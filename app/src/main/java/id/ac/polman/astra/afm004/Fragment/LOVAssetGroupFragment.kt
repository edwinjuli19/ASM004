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
import id.ac.polman.astra.afm004.LOVAssetGroupAdapter
import id.ac.polman.astra.afm004.Models.LOVAssetGroup
import id.ac.polman.astra.afm004.R

class LOVAssetGroupFragment : Fragment(), LOVAssetGroupAdapter.OnItemClickListener, NotesFragment.OnNotesSavedListener {

    private lateinit var adapter: LOVAssetGroupAdapter
    private var listener: OnItemClickListener? = null
    private var assetGroupList: List<LOVAssetGroup> = listOf()
    private var filteredAssetGroupList: List<LOVAssetGroup> = listOf()
    private var assetGroup: LOVAssetGroup? = null
    private var currentNotes: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lov_asset_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_lov_asset_group)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LOVAssetGroupAdapter(filteredAssetGroupList, this)
        recyclerView.adapter = adapter

        // Initialize the list of asset groups
        assetGroupList = getListAssetGroup()
        filteredAssetGroupList = assetGroupList
        adapter.updateList(filteredAssetGroupList)

        // Get current notes from arguments
        currentNotes = arguments?.getString("current_notes")
        Log.d("currentNotes", currentNotes.toString())
    }

    fun filterList(query: String) {
        filteredAssetGroupList = assetGroupList.filter {
            it.asset_group_number.contains(query, ignoreCase = true) ||
                    it.asset_group_description.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredAssetGroupList)
    }

    private fun getListAssetGroup(): List<LOVAssetGroup> {
        return listOf(
            LOVAssetGroup("A01", "Office Equipment"),
            LOVAssetGroup("A02", "Vehicles"),
            // Add more data as needed
        )
    }

    override fun onItemClick(assetGroup: LOVAssetGroup, current_notes: String) {
        this.assetGroup = assetGroup
        showNotesFragment(currentNotes.toString(), "lov_asset_group")
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
        fun onAssetGroupSelected(assetGroup: LOVAssetGroup?, notes: String)
    }

    override fun onNotesSaved(notes: String, notes_type: String) {
        Log.d("onNotesSaved", notes)
        Log.d("onNotesSaved", notes_type)
        if (notes_type == "lov_asset_group") {
            listener?.onAssetGroupSelected(this.assetGroup, notes)
        }
    }
}