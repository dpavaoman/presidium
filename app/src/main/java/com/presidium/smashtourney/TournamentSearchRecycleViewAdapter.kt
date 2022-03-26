package com.presidium.smashtourney

import androidx.navigation.Navigation.findNavController
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import apolloClient
import com.apollographql.apollo3.exception.ApolloHttpException
import com.presidium.smashtourney.dao.searchResults.ResultType
import com.presidium.smashtourney.dao.searchResults.SearchResult
import com.presidium.smashtourney.dao.title.Title
import com.presidium.smashtourney.databinding.FragmentTitleResultBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * [RecyclerView.Adapter] that can display a [com.presidium.smashtourney.dao.title.Title].
 */
class TournamentSearchRecycleViewAdapter(private val titles: Array<Title?>) :
    RecyclerView.Adapter<TournamentSearchRecycleViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                FragmentTitleResultBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title = titles[position]!!
        holder.titleName.text = titles[position]!!.name
    }
    
    override fun getItemCount(): Int {
        return titles.size
    }
    
    inner class ViewHolder(binding: FragmentTitleResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleName: TextView
        lateinit var title: Title
        override fun toString(): String {
            return super.toString() + " '"
        }
        
        init {
            val view: View = binding.root
            view.setOnClickListener { view ->
                
                val searchResultArray = CoroutineScope(Dispatchers.IO).async {
                    val finalResult = apolloClient.query(TournamentsByVideogameQuery(title.id.toString()))
                        .execute()
                    val resultList: ArrayList<SearchResult> = ArrayList()
                    finalResult.data?.tournaments?.nodes?.forEach {
                        val result = SearchResult(it?.id, ResultType.TOURNAMENT, it?.name)
                        resultList.add(result)
                    }
                    resultList.toTypedArray()
                }
                CoroutineScope(Dispatchers.Main).launch {
                    val action = TournamentSearchFragmentDirections.actionTournamentSearchFragmentToSearchResultFragment(searchResultArray.await())
                    findNavController(view).navigate(action)
                }
            }
            titleName = binding.itemNumber
        }
    }
}