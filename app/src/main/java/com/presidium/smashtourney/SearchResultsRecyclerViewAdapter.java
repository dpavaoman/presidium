package com.presidium.smashtourney;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.presidium.smashtourney.dao.searchResults.SearchResult;
import com.presidium.smashtourney.databinding.FragmentSearchResultBinding;

/**
 * {@link RecyclerView.Adapter} that can display a {@link SearchResult}.
 * TODO: Replace the implementation with code for your data type.
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
		holder.mItem = results[position];
		holder.mIdView.setText(results[position].getName());
	}

	@Override
	public int getItemCount() {
		return results.length;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public final TextView mIdView;
		public SearchResult mItem;

		public ViewHolder(FragmentSearchResultBinding binding) {
			super(binding.getRoot());
			mIdView = binding.itemNumber;
		}

		@Override
		public String toString() {
			return super.toString() + " '";
		}
	}
}