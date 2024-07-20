package id.ac.polman.astra.afm004.Fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.LOVAssetStatusAdapter
import id.ac.polman.astra.afm004.Models.LOVAssetStatus
import id.ac.polman.astra.afm004.R

class LOVAssetStatusFragment : Fragment(), LOVAssetStatusAdapter.OnItemClickListener {

    private var listener: OnItemClickListener? = null
    private lateinit var adapter: LOVAssetStatusAdapter
    private val fullList = getListAssetStatus()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lov_asset_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_assetstatus)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LOVAssetStatusAdapter(fullList, this)
        recyclerView.adapter = adapter

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

    private fun getListAssetStatus(): List<LOVAssetStatus> {
        return listOf(
            LOVAssetStatus("USE - GOOD"),
            LOVAssetStatus("NOT USED - POOR"),
            LOVAssetStatus("NOT USED - BEING REPAIRED"),
            LOVAssetStatus("NOT USED - GOOD"),
            LOVAssetStatus("NOT USED - READY SALE/SCRAP"),
            LOVAssetStatus("ABSENT(LOST/STOLEN)"),
            LOVAssetStatus("SOLD"),
            LOVAssetStatus("NOT IDENTIFIED")
        )
    }

    fun filterList(query: String) {
        val filteredList = fullList.filter {
            it.asset_status_description.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredList)
    }

    override fun onItemClick(assetStatus: LOVAssetStatus) {
        listener?.onAssetStatusSelected(assetStatus)
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
        fun onAssetStatusSelected(assetStatus: LOVAssetStatus)
    }
}