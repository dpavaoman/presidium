package com.presidium.smashtourney;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.presidium.smashtourney.dao.EventStandingsQuery;
import com.presidium.smashtourney.dao.TournamentsByVideogameQuery;
import com.presidium.smashtourney.dao.queries.TournamentsByTitleQuery;
import com.presidium.smashtourney.dao.searchResults.SearchResult;
import com.presidium.smashtourney.dao.title.Title;
import com.presidium.smashtourney.databinding.FragmentTitleResultBinding;
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
 * {@link RecyclerView.Adapter} that can display a {@link com.presidium.smashtourney.dao.title.Title}.
 */
public class TournamentSearchRecycleViewAdapter extends RecyclerView.Adapter<TournamentSearchRecycleViewAdapter.ViewHolder> {

	private final Title[] titles;

	public TournamentSearchRecycleViewAdapter(Title[] items) {
		titles = items;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		return new ViewHolder(FragmentTitleResultBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, int position) {
		holder.title = titles[position];
		holder.titleName.setText(titles[position].getName());
	}

	@Override
	public int getItemCount() {
		return titles.length;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public final TextView titleName;
		public Title title;

		public ViewHolder(FragmentTitleResultBinding binding) {
			super(binding.getRoot());
			View view = binding.getRoot();
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					QueryRetrievalService service = new QueryRetrievalService();
					Map<String, String> variables = new HashMap<>();
					variables.put("videogameid", title.id.toString());
					SearchResult[] finalResult = service.getSearchResults(variables, new TournamentsByVideogameQuery());

					try {
						NavDirections action = TournamentSearchFragmentDirections.actionTournamentSearchFragmentToSearchResultFragment(finalResult);
						Navigation.findNavController(view).navigate(action);
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			});
		titleName =binding.itemNumber;

	}

	@Override
	public String toString() {
		return super.toString() + " '";
	}
}
}