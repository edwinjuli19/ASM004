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
import id.ac.polman.astra.afm004.LOVDocumentTypeAdapter
import id.ac.polman.astra.afm004.Models.LOVDocumentType
import id.ac.polman.astra.afm004.R

class LOVDocumentTypeFragment : Fragment(), LOVDocumentTypeAdapter.OnItemClickListener {

    private var listener: OnItemClickListener? = null
    private lateinit var adapter: LOVDocumentTypeAdapter
    private val fullList = getListDocumentTypes()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lov_document_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_lov_document_type)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LOVDocumentTypeAdapter(fullList, this)
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

    private fun getListDocumentTypes(): List<LOVDocumentType> {
        return listOf(
            LOVDocumentType("Building", "Foto Tampak Depan"),
            LOVDocumentType("Machine", "Foto Tampak Depan"),
            LOVDocumentType("Vehicle", "Foto Tampak Depan"),
            // Add more document types as needed
        )
    }

    fun filterList(query: String) {
        val filteredList = fullList.filter {
            it.asset_type.contains(query, ignoreCase = true) ||
                    it.document_type.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredList)
    }

    override fun onItemClick(documentType: LOVDocumentType) {
        listener?.onDocumentTypeSelected(documentType)
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
        fun onDocumentTypeSelected(documentType: LOVDocumentType)
    }
}
