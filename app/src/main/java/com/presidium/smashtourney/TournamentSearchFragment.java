package com.presidium.smashtourney;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.presidium.smashtourney.dao.title.Title;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TournamentSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TournamentSearchFragment extends Fragment {

	public TournamentSearchFragment() {
		// Required empty public constructor
	}


	public static TournamentSearchFragment newInstance() {
		TournamentSearchFragment fragment = new TournamentSearchFragment();
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
		View view = inflater.inflate(R.layout.fragment_tournament_search, container, false);
		Title[] titles = new Title[2];
		titles[0] = Title.MELEE;
		titles[1] = Title.PM;
		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;

			recyclerView.setLayoutManager(new LinearLayoutManager(context));

			recyclerView.setAdapter(new TournamentSearchRecycleViewAdapter(titles));
		}
		return view;

	}

	@Override
	public void onResume() {
		super.onResume();
	}
}