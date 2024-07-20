package id.ac.polman.astra.afm004.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.ListObservation
import id.ac.polman.astra.afm004.ListObservationAdapter
import id.ac.polman.astra.afm004.R

class ListObservationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_observation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_listObservation)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ListObservationAdapter(getListObservationData())
    }

    private fun getListObservationData(): List<ListObservation> {
        // Return the list of observations
        return listOf(
            ListObservation("CRANE HOIST 1,5 TON DEMAG v1"),
            ListObservation("BANGUNAN NEW PLANT 4"),
            ListObservation("SRBTC"),
            ListObservation("Pompa Hydrant")
        )
    }
}
