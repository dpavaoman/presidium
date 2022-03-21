package com.presidium.smashtourney;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {



	public MainFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @return A new instance of fragment MainFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static MainFragment newInstance() {
		MainFragment fragment = new MainFragment();
		Bundle args = new Bundle();

		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		view.findViewById(R.id.titleButton).setOnClickListener(view12 -> {
			NavDirections action = MainFragmentDirections.actionMainFragmentToTournamentSearchFragment();
			Navigation.findNavController(view12).navigate(action);
		});
		view.findViewById(R.id.eventButton).setOnClickListener(view1 -> {
			NavDirections action = MainFragmentDirections.actionMainFragmentToStandingsSearchFragment();
			Navigation.findNavController(view1).navigate(action);
		});
		return view;
	}
}