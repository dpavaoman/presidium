package com.presidium.smashtourney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.presidium.smashtourney.dao.EventStandingsQuery;
import com.presidium.smashtourney.dao.queries.EventStandingsQueryString;
import com.presidium.smashtourney.dao.searchResults.SearchResult;
import com.presidium.smashtourney.domain.QueryRetrievalCallable;
import com.presidium.smashtourney.domain.QueryRetrievalService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StandingsSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StandingsSearchFragment extends Fragment {

	public StandingsSearchFragment() {
		// Required empty public constructor
	}


	public static StandingsSearchFragment newInstance() {
		StandingsSearchFragment fragment = new StandingsSearchFragment();
		Bundle args = new Bundle();

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
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_standings_search, container, false);
		EditText searchBox = view.findViewById(R.id.search_box);
		view.findViewById(R.id.search_button).setOnClickListener(view1 -> {

			String eventId = searchBox.getText().toString();

			QueryRetrievalService service = new QueryRetrievalService();
			Map<String, String> variables = new HashMap<>();
			variables.put("eventId", eventId);
			SearchResult[] finalResult = service.getSearchResults(variables, new EventStandingsQuery());

			try {
				NavDirections action = StandingsSearchFragmentDirections.actionStandingsSearchFragmentToSearchResultFragment(finalResult);
				Navigation.findNavController(view).navigate(action);
			} catch(Exception e){
				e.printStackTrace();
			}
		});
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
	}
}