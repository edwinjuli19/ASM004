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
import id.ac.polman.astra.afm004.LOVAssetLocationAdapter
import id.ac.polman.astra.afm004.Models.LOVAssetLocation
import id.ac.polman.astra.afm004.R

class LOVAssetLocationFragment : Fragment(), LOVAssetLocationAdapter.OnItemClickListener {

    private var listener: OnItemClickListener? = null
    private lateinit var adapter: LOVAssetLocationAdapter
    private val fullList = getListAssetLocation()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lov_asset_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_assetlocation)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LOVAssetLocationAdapter(fullList, this)
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

    private fun getListAssetLocation(): List<LOVAssetLocation> {
        return listOf(
            LOVAssetLocation("10100001", "Land"),
            LOVAssetLocation("10100002", "Machine"),
            LOVAssetLocation("10100003", "Vehicle"),
            LOVAssetLocation("10100004", "Furniture"),
            LOVAssetLocation("10100005", "Electronics"),
            LOVAssetLocation("10100006", "Building")
            // Add more data as needed
        )
    }

    fun filterList(query: String) {
        val filteredList = fullList.filter {
            it.asset_location_number.contains(query, ignoreCase = true) ||
                    it.asset_location_description.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredList)
    }

    override fun onItemClick(assetLocation: LOVAssetLocation) {
        listener?.onAssetLocationSelected(assetLocation)
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
        fun onAssetLocationSelected(assetLocation: LOVAssetLocation)
    }
}