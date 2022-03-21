package com.presidium.smashtourney;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.presidium.smashtourney.dao.searchResults.ResultType;
import com.presidium.smashtourney.dao.searchResults.SearchResult;
import com.presidium.smashtourney.databinding.FragmentSearchResultBinding;
import com.presidium.smashtourney.domain.QueryRetrievalService;
import com.presidium.smashtourney.dao.EventsByTournamentQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a {@link SearchResult}.
 */
public class SearchResultsRecyclerViewAdapter extends RecyclerView.Adapter<SearchResultsRecyclerViewAdapter.ViewHolder> {

	private final SearchResult[] results;

	public SearchResultsRecyclerViewAdapter(SearchResult[] items) {
		results = items;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		return new ViewHolder(FragmentSearchResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		holder.searchResult = results[position];
		holder.idView.setText(results[position].getName());
	}

	@Override
	public int getItemCount() {
		return results.length;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public final TextView idView;
		public SearchResult searchResult;

		public ViewHolder(FragmentSearchResultBinding binding) {
			super(binding.getRoot());
			View view = binding.getRoot();
				view.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {
						if (searchResult.getResultType() == ResultType.TOURNAMENT) {

							QueryRetrievalService service = new QueryRetrievalService();
							Map<String, String> variables = new HashMap<>();
							variables.put("tournamentId", searchResult.getId());
							SearchResult[] finalResult = service.getSearchResults(variables, new EventsByTournamentQuery());

							try {
								NavDirections action = SearchResultFragmentDirections.actionSearchResultFragmentSelf(finalResult);
								Navigation.findNavController(view).navigate(action);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});


			idView = binding.itemNumber;
		}

		@Override
		public String toString() {
			return super.toString() + " '";
		}
	}
}