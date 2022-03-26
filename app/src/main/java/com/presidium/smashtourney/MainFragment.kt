package com.presidium.smashtourney

import androidx.navigation.Navigation.findNavController
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        view.findViewById<View>(R.id.titleButton).setOnClickListener { view12: View? ->
            val action = MainFragmentDirections.actionMainFragmentToTournamentSearchFragment()
            findNavController(view12!!).navigate(action)
        }
        view.findViewById<View>(R.id.eventButton).setOnClickListener { view1: View? ->
            val action = MainFragmentDirections.actionMainFragmentToStandingsSearchFragment()
            findNavController(view1!!).navigate(action)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}