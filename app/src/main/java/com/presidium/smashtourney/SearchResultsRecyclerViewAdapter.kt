package com.presidium.smashtourney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import apolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.presidium.smashtourney.dao.searchResults.ResultType
import com.presidium.smashtourney.dao.searchResults.SearchResult
import com.presidium.smashtourney.databinding.FragmentSearchResultBinding
import kotlinx.coroutines.*

/**
 * [RecyclerView.Adapter] that can display a [SearchResult].
 */
class SearchResultsRecyclerViewAdapter(private val results: Array<SearchResult>) :
    RecyclerView.Adapter<SearchResultsRecyclerViewAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                FragmentSearchResultBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.searchResult = results[position]
        holder.idView.text = results[position].name
    }
    
    override fun getItemCount(): Int {
        return results.size
    }
    
    class ViewHolder(binding: FragmentSearchResultBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView
        lateinit var searchResult: SearchResult
        override fun toString(): String {
            return super.toString() + " '"
        }
        
        init {
            val view: View = binding.root
            view.setOnClickListener { view ->
                if (searchResult.id != null) {
                    
                    if (searchResult.resultType == ResultType.TOURNAMENT) {
                        var finalResult: ApolloResponse<EventsByTournamentQuery.Data>
                        val searchResultArray = CoroutineScope(Dispatchers.IO).async {
                            finalResult = apolloClient.query(EventsByTournamentQuery(searchResult.id!!))
                                .execute()
                            
                            val resultList: ArrayList<SearchResult> = ArrayList()
                            finalResult.data?.tournament?.events?.forEach() {
                                val result = SearchResult(it?.id, ResultType.EVENT, it?.name)
                                resultList.add(result)
                            }
                            resultList.toTypedArray()
                        }
                        CoroutineScope(Dispatchers.Main).launch {
                            val action = SearchResultFragmentDirections.actionSearchResultFragmentSelf(searchResultArray.await())
                            findNavController(view).navigate(action)
                        }
                        
                    } else if (searchResult.resultType == ResultType.EVENT) {
                        val searchResultArray = CoroutineScope(Dispatchers.IO).async {
                            val finalResult = apolloClient.query(StandingsByEventQuery(searchResult.id!!))
                                .execute()
                            val resultList: ArrayList<SearchResult> = ArrayList()
                            finalResult.data?.event?.standings?.nodes?.forEach {
                                val result = SearchResult(it?.entrant?.id, ResultType.STANDING, it?.entrant?.name)
                                resultList.add(result)
                            }
                            resultList.toTypedArray()
                        }
                        CoroutineScope(Dispatchers.Main).launch {
                            val action = SearchResultFragmentDirections.actionSearchResultFragmentSelf(searchResultArray.await())
                            findNavController(view).navigate(action)
                        }
                    }
                }
            }
            idView = binding.itemNumber
        }
        
    }
    
    
}