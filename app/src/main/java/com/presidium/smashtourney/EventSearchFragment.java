package com.presidium.smashtourney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.presidium.smashtourney.dao.queries.EventStandingsQueryString;
import com.presidium.smashtourney.dao.searchResults.SearchResult;
import com.presidium.smashtourney.domain.QueryRetrieval;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventSearchFragment extends Fragment {

	public EventSearchFragment() {
		// Required empty public constructor
	}


	public static EventSearchFragment newInstance() {
		EventSearchFragment fragment = new EventSearchFragment();
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
		View view = inflater.inflate(R.layout.fragment_event_search, container, false);
		EditText searchBox = view.findViewById(R.id.search_box);
		view.findViewById(R.id.search_button).setOnClickListener(view1 -> {
			String eventId = searchBox.getText().toString();
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			String query = EventStandingsQueryString.query;
			Map<String, String> variables = new HashMap<>();
			variables.put("eventId", eventId);
			Future<SearchResult[]> future = executorService.submit(new QueryRetrieval(variables, query, "event"));
			executorService.shutdown();
			//Shutting down the executor service because we don't need it any more
			try {
				if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
					System.out.println("timeout");
					executorService.shutdownNow();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				executorService.shutdownNow();
			}
			SearchResult[] resultList =  null;
			try {
				resultList = future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			SearchResult[] finalResult = resultList;

			try {
				NavDirections action = EventSearchFragmentDirections.actionEventSearchFragmentToSearchResultFragment(finalResult);
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