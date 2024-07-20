package id.ac.polman.astra.afm004.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.LOVPlantIdAdapter
import id.ac.polman.astra.afm004.Models.LOVPlantId
import id.ac.polman.astra.afm004.R

class LOVPlantIdFragment : Fragment(), LOVPlantIdAdapter.OnItemClickListener, NotesFragment.OnNotesSavedListener {

    private lateinit var adapter: LOVPlantIdAdapter
    private var listener: OnItemClickListener? = null
    private var plantIdList: List<LOVPlantId> = listOf()
    private var filteredPlantIdList: List<LOVPlantId> = listOf()
    private var plantId: LOVPlantId? = null
    private var currentNotes: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lov_plant_id, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_plantid)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LOVPlantIdAdapter(filteredPlantIdList, this)
        recyclerView.adapter = adapter

        // Initialize the list of plant IDs
        plantIdList = getListPlantId()
        filteredPlantIdList = plantIdList
        adapter.updateList(filteredPlantIdList)

        // Get current notes from arguments
        currentNotes = arguments?.getString("current_notes")

    }

    fun filterList(query: String) {
        filteredPlantIdList = plantIdList.filter {
            it.plant_id_number.contains(query, ignoreCase = true) || it.plant_id_description.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredPlantIdList)
    }

    private fun getListPlantId(): List<LOVPlantId> {
        return listOf(
            LOVPlantId("10200", "Building"),
            LOVPlantId("10201", "Machine"),
            LOVPlantId("10202", "Vehicle"),
            LOVPlantId("10203", "Furniture")
        )
    }

    override fun onItemClick(plantId: LOVPlantId) {
        Log.d("onItemClick", plantId.plant_id_number);
        Log.d("onItemClick", plantId.plant_id_description);
        this.plantId = plantId
        showNotesFragment(this.currentNotes.toString(), "lov_plant_id")
    }

    private fun showNotesFragment(notes: String, notesType: String) {
        // Create and show your fragment for notes input
        val notesFragment = NotesFragment()
        notesFragment.setTargetFragment(this, 0) // Set target fragment
        notesFragment.setOnNotesSavedListener(this)

        // Create a bundle to pass the current value of notes
        val args = Bundle()
        args.putString("current_notes", notes)
        args.putString("notes_type", notesType)
        notesFragment.arguments = args

        // Show the fragment
        notesFragment.show(parentFragmentManager, "NotesFragment")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItemClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnItemClickListener {
        fun onPlantIdSelected(plantId: LOVPlantId?, notes: String)
    }

    override fun onNotesSaved(notes: String, notesType: String) {
        Log.d("onNotesSaved", notes)
        Log.d("onNotesSaved", notesType)

        if (notesType == "lov_plant_id") {
            listener?.onPlantIdSelected(this.plantId, notes)
        }
    }
}