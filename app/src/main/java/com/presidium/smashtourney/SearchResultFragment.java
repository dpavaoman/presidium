package com.presidium.smashtourney;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.presidium.smashtourney.dao.searchResults.SearchResult;
import com.presidium.smashtourney.placeholder.PlaceholderContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class SearchResultFragment extends Fragment {

	// TODO: Customize parameter argument names
	private static final String SEARCH_RESULTS = "searchResults";
	// TODO: Customize parameters

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public SearchResultFragment() {
	}

	// TODO: Customize parameter initialization
	@SuppressWarnings("unused")
	public static SearchResultFragment newInstance(SearchResult[] searchResults) {
		SearchResultFragment fragment = new SearchResultFragment();
		Bundle args = new Bundle();
		args.putParcelableArray(SEARCH_RESULTS, searchResults);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_search_result_list, container, false);
		SearchResult[] searchResults = SearchResultFragmentArgs.fromBundle(getArguments()).getSearchResult();
		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;

			recyclerView.setLayoutManager(new LinearLayoutManager(context));

			recyclerView.setAdapter(new SearchResultsRecyclerViewAdapter(searchResults));
		}
		return view;
	}
}