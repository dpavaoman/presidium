package com.presidium.smashtourney

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.presidium.smashtourney.dao.searchResults.SearchResult

/**
 * A fragment representing a list of Items.
 */
class SearchResultFragment  // TODO: Customize parameters
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
    : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_result_list, container, false)
        val searchResults = arguments?.let { SearchResultFragmentArgs.fromBundle(it).searchResult }
        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = searchResults?.let { SearchResultsRecyclerViewAdapter(it) }
        }
        return view
    }
    
    companion object {
        // TODO: Customize parameter argument names
        private const val SEARCH_RESULTS = "searchResults"
        
        // TODO: Customize parameter initialization
        fun newInstance(searchResults: Array<SearchResult>): SearchResultFragment {
            val fragment = SearchResultFragment()
            val args = Bundle()
            args.putParcelableArray(SEARCH_RESULTS, searchResults)
            fragment.arguments = args
            return fragment
        }
    }
}