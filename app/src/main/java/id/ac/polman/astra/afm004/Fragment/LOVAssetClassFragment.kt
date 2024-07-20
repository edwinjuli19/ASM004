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
import id.ac.polman.astra.afm004.LOVAssetClassAdapter
import id.ac.polman.astra.afm004.Models.LOVAssetClass
import id.ac.polman.astra.afm004.R

class LOVAssetClassFragment() : Fragment(), LOVAssetClassAdapter.OnItemClickListener,NotesFragment.OnNotesSavedListener {

    private lateinit var adapter: LOVAssetClassAdapter
    private var listener: OnItemClickListener? = null
    private var assetClassList: List<LOVAssetClass> = listOf()
    private var filteredAssetClassList: List<LOVAssetClass> = listOf()
    private var assetClass : LOVAssetClass?=null
    private var currentNotes: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lov_asset_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_lov_asset_class)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LOVAssetClassAdapter(filteredAssetClassList, this)
        recyclerView.adapter = adapter

        // Initialize the list of asset classes
        assetClassList = getListAssetClass()
        filteredAssetClassList = assetClassList
        adapter.updateList(filteredAssetClassList)

        // Get current notes from arguments
        currentNotes = arguments?.getString("current_notes")
        Log.d("currentNotes", currentNotes.toString());
    }

    fun filterList(query: String) {
        filteredAssetClassList = assetClassList.filter {
            it.asset_class_number.contains(query, ignoreCase = true) || it.asset_class_description.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredAssetClassList)
    }

    private fun getListAssetClass(): List<LOVAssetClass> {
        return listOf(
            LOVAssetClass("001", "Vehicle"),
            LOVAssetClass("002", "Machinery"),
            // Add more data as needed
        )
    }

    override fun onItemClick(assetClass: LOVAssetClass, current_notes: String) {
        this.assetClass=assetClass
        showNotesFragment(currentNotes.toString(), "lov_asset_class")
    }


    private fun showNotesFragment(notes: String, notes_type: String) {
        // Create and show your fragment for notes input
        val notesFragment = NotesFragment()
        notesFragment.setTargetFragment(this, 0) // Set target fragment
        notesFragment.setOnNotesSavedListener(this)

        // Create a bundle to pass the current value of assetDescription
        val args = Bundle()
        args.putString("current_notes", notes)
        args.putString("notes_type", notes_type)
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
        fun onAssetClassSelected(assetClass: LOVAssetClass?, notes: String)
    }

    override fun onNotesSaved(notes: String, notes_type: String) {
        Log.d("onNotesSaved", notes)
        Log.d("onNotesSaved", notes_type)
        if(notes_type == "lov_asset_class") {
            listener?.onAssetClassSelected(this.assetClass, notes)
        }
    }
}