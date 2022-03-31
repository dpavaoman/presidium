package com.presidium.smashtourney

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import apolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.presidium.smashtourney.dao.searchResults.SearchResult
import com.presidium.smashtourney.dao.composables.ResultList
import com.presidium.smashtourney.dao.searchResults.ResultType

import com.presidium.smashtourney.databinding.FragmentSearchResultListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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
    
    private var _binding: FragmentSearchResultListBinding? = null
    
    private val binding get() = _binding!!
    
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultListBinding.inflate(inflater, container, false)
        
        val view = binding.root
        val searchResults = arguments?.let { SearchResultFragmentArgs.fromBundle(it).searchResult }
        // Set the adapter
        binding.searchResultComposeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                MaterialTheme {
                    if (searchResults!!.size == 1) {
                        CircularProgressIndicator(
                                color = MaterialTheme.colors.primary,
                                modifier = Modifier
                                    .padding(horizontal = 6.dp)
                                    .size(36.dp)
                        )
                    }
                    else {
                        val title = searchResults[0].name!!
                        val filteredArray = searchResults.slice(IntRange(1, searchResults.size-1)).toTypedArray()
                        ResultList(searchResults = filteredArray, onNavigate = { dest -> findNavController().navigate(dest) }, title = title)
                    }
                }
            }
        }
        return view
    }
    
    override fun onResume() {
        super.onResume()
        val searchResults = arguments?.let { SearchResultFragmentArgs.fromBundle(it).searchResult }
        if (searchResults != null && searchResults.size == 1) {
            val searchResult = searchResults[0]
            
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
                CoroutineScope(Dispatchers.Default).launch {
                    val newSearchResults = searchResultArray.await()
                    binding.searchResultComposeView.apply {
                        setContent {
                            MaterialTheme {
                                ResultList(searchResults = newSearchResults, onNavigate = { dest -> findNavController().navigate(dest) }, title = searchResult.name!!)
                            }
                        }
                    }
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
                CoroutineScope(Dispatchers.Default).launch {
                    val newSearchResults = searchResultArray.await()
                    binding.searchResultComposeView.apply {
                        setContent {
                            MaterialTheme {
                                ResultList(searchResults = newSearchResults, onNavigate = { dest -> findNavController().navigate(dest) }, title = searchResult.name!!)
                            }
                        }
                    }
                }
            }
        }
        
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
    
    @Composable
    fun ResultList(
        title: String,
        searchResults: Array<SearchResult>,
        onNavigate: (NavDirections) -> (Unit)) {
        LazyColumn {
            // Add a single item as a header
            item {
                Text(text = title)
            }
            // Add list of messages
            items(searchResults) { item: SearchResult ->
                ResultCard(searchResult = item, onNavigate = onNavigate)
            }
        }
    }
    
    @Composable
    fun ResultCard(
        searchResult: SearchResult,
        onNavigate: (NavDirections) -> Unit) {// Add padding around our message
        val searchResultQuery = arrayOf(searchResult)
        Row(
                Modifier
                    .clickable {
                        onNavigate(SearchResultFragmentDirections.actionSearchResultFragmentSelf(searchResultQuery))
                    }
                    .padding(all = 8.dp)
        ) {
            Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Contact profile picture",
                    modifier = Modifier
                        // Set image size to 40 dp
                        .size(40.dp)
                        // Clip image to be shaped as a circle
                        .clip(CircleShape)
            )
            
            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))
            
            Column {
                Text(text = searchResult.name!!)
                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = searchResult.id!!)
            }
        }
    }
    
    @Preview
    @Composable
    fun PreviewCards() {
        val results = arrayOf(SearchResult("1", ResultType.EVENT, "Abate"), SearchResult("2", ResultType.EVENT, "Plup"))
        MaterialTheme {
            ResultList(
                    searchResults = results, onNavigate = { dest -> findNavController().navigate(dest) }, title = "luigI"
            )
        }
    }
    
}