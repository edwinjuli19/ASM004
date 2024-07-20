package id.ac.polman.astra.afm004.Fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.LOVCostCenterDeprectiationAdapter
import id.ac.polman.astra.afm004.Models.LOVCostCenterDepreciation
import id.ac.polman.astra.afm004.R

class LOVCostCenterDepreciationFragment : Fragment(), LOVCostCenterDeprectiationAdapter.OnItemClickListener, NotesFragment.OnNotesSavedListener {

    private lateinit var adapter: LOVCostCenterDeprectiationAdapter
    private var listener: OnItemClickListener? = null
    private var ccdList: List<LOVCostCenterDepreciation> = listOf()
    private var filteredCcdList: List<LOVCostCenterDepreciation> = listOf()
    private var ccd: LOVCostCenterDepreciation? = null
    private var currentNotes: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lov_cost_center_deprectiation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_ccd)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LOVCostCenterDeprectiationAdapter(filteredCcdList, this)
        recyclerView.adapter = adapter

        // Initialize the list of cost center depreciations
        ccdList = getListCCD()
        filteredCcdList = ccdList
        adapter.updateList(filteredCcdList)

        // Get current notes from arguments
        currentNotes = arguments?.getString("current_notes")
        Log.d("currentNotes", currentNotes.toString())

        val searchEditText = requireActivity().findViewById<EditText>(R.id.search_view)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                filterList(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    fun filterList(query: String) {
        filteredCcdList = ccdList.filter {
            it.ccd_number.contains(query, ignoreCase = true) || it.ccd_description.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredCcdList)
    }

    private fun getListCCD(): List<LOVCostCenterDepreciation> {
        return listOf(
            LOVCostCenterDepreciation("PEE000", "PROC ENGINEERING E"),
            LOVCostCenterDepreciation("PEE001", "PROC ENGINEERING F")
            // Add more data as needed
        )
    }

    override fun onItemClick(costCenterDepreciation: LOVCostCenterDepreciation, currentNotes: String) {
        this.ccd = costCenterDepreciation
        showNotesFragment(currentNotes, "lov_cost_center_depreciation")
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
        fun onCostCenterDepreciationSelected(costCenterDepreciation: LOVCostCenterDepreciation?, notes: String)
    }

    override fun onNotesSaved(notes: String, notesType: String) {
        Log.d("onNotesSaved", notes)
        Log.d("onNotesSaved", notesType)
        if (notesType == "lov_cost_center_depreciation") {
            listener?.onCostCenterDepreciationSelected(this.ccd, notes)
        }
    }
}
