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
import id.ac.polman.astra.afm004.LOVActivityTypeAdapter
import id.ac.polman.astra.afm004.Models.LOVActivityType
import id.ac.polman.astra.afm004.R

class LOVActivityTypeFragment : Fragment(), LOVActivityTypeAdapter.OnItemClickListener {

    private var listener: OnItemClickListener? = null
    private lateinit var adapter: LOVActivityTypeAdapter
    private val fullList = getListActivityType()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lov_activity_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_activity_type)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LOVActivityTypeAdapter(fullList, this)
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

    private fun getListActivityType(): List<LOVActivityType> {
        return listOf(
            LOVActivityType("10200", "Building"),
            LOVActivityType("10201", "Machine"),
            LOVActivityType("10202", "Vehicle"),
            LOVActivityType("10203", "Furniture")
        )
    }

    fun filterList(query: String) {
        val filteredList = fullList.filter {
            it.activity_type_number.contains(query, ignoreCase = true) ||
                    it.activity_type_description.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredList)
    }

    override fun onItemClick(activityType: LOVActivityType) {
        listener?.onActivityTypeSelected(activityType)
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
        fun onActivityTypeSelected(activityType: LOVActivityType)
    }
}