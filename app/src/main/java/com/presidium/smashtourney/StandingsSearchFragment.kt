package com.presidium.smashtourney

import androidx.navigation.Navigation.findNavController
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import apolloClient
import com.presidium.smashtourney.dao.searchResults.ResultType
import com.presidium.smashtourney.dao.searchResults.SearchResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [StandingsSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StandingsSearchFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_standings_search, container, false)
        
        return view
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchBox = view.findViewById<EditText>(R.id.search_box)
        
        view.findViewById<View>(R.id.search_button).setOnClickListener {
            val eventId = searchBox.text.toString()
            
            val searchResultArray = CoroutineScope(Dispatchers.IO).async {
                val finalResult = apolloClient.query(StandingsByEventQuery(eventId))
                    .execute()
                
                val data: StandingsByEventQuery.Data? = finalResult.data
                val resultList: ArrayList<SearchResult> = ArrayList()
                data?.event?.standings?.nodes?.forEach {
                    val result = SearchResult(it?.entrant?.id, ResultType.STANDING, it?.entrant?.name)
                    resultList.add(result)
                }
                resultList.toTypedArray()
            }
            CoroutineScope(Dispatchers.Main).launch {
                val action = StandingsSearchFragmentDirections.actionStandingsSearchFragmentToSearchResultFragment(searchResultArray.await())
                findNavController(view).navigate(action)
            }
            
        }
    }
    
    override fun onResume() {
        super.onResume()
    }
    
    companion object {
        fun newInstance(): StandingsSearchFragment {
            val fragment = StandingsSearchFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}