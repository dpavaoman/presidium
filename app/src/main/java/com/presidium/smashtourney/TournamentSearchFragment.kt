package com.presidium.smashtourney

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.Fragment
import com.presidium.smashtourney.dao.title.Title

/**
 * A simple [Fragment] subclass.
 * Use the [TournamentSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TournamentSearchFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tournament_search, container, false)
        val titles = arrayOfNulls<Title>(2)
        titles[0] = Title.MELEE
        titles[1] = Title.PM
        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = TournamentSearchRecycleViewAdapter(titles)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {
        fun newInstance(): TournamentSearchFragment {
            val fragment = TournamentSearchFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}