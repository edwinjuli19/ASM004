package id.ac.polman.astra.afm004.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Activity.ObservationActivity
import id.ac.polman.astra.afm004.HistoryObservation
import id.ac.polman.astra.afm004.HistoryObservationAdapter
import id.ac.polman.astra.afm004.ListObservation
import id.ac.polman.astra.afm004.R

class HistoryObservationFragment : Fragment() {
    private lateinit var historyObservationAdapter: HistoryObservationAdapter
    private var observationList: List<HistoryObservation> = mutableListOf() // Isi dengan data yang sesuai

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_history_observation, container, false)

        // Inisialisasi RecyclerView dan adapter
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view_historyobservation)
        historyObservationAdapter = HistoryObservationAdapter(observationList)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyObservationAdapter
        }


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView di sini jika diperlukan
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_historyobservation)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = HistoryObservationAdapter(getObservationData()) // Gantilah dengan adapter dan data Anda
    }

    private fun getObservationData(): List<HistoryObservation> {
        // Contoh data pengamatan, gantilah dengan data sesuai kebutuhan
        return listOf(
            HistoryObservation("CRANE HOIST 1,5 TON DEMAG v1"),
            HistoryObservation("BANGUNAN NEW PLANT 4"),
            HistoryObservation("SRBTC"),
            HistoryObservation("Pompa Hydrant")
        )
    }
}
