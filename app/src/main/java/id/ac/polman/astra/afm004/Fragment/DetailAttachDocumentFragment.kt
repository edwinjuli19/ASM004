package id.ac.polman.astra.afm004.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.DetailAttachDocumentAdapter
import id.ac.polman.astra.afm004.LOVAssetClassAdapter
import id.ac.polman.astra.afm004.LOVPlantIdAdapter
import id.ac.polman.astra.afm004.Models.DetailAttachDocument
import id.ac.polman.astra.afm004.Models.LOVAssetClass
import id.ac.polman.astra.afm004.Models.LOVPlantId
import id.ac.polman.astra.afm004.R

class DetailAttachDocumentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_attach_document, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.list_view_detail_attach_document)
        val adapter = DetailAttachDocumentAdapter(requireContext(), getlistplantid())
        listView.adapter = adapter
        setListViewHeightBasedOnChildren(listView)
    }


    private fun getlistplantid(): List<DetailAttachDocument> {
        return listOf(
            DetailAttachDocument("foto full mesin", "00895.A1.png","HC7J+HFR, Kamojing, Kec. Cikampek, Karawang, Jawa Barat 41373"),
            DetailAttachDocument("foto tampak depan", "00895.A1.png","HC7J+HFR, Kamojing, Kec. Cikampek, Karawang, Jawa Barat 41373"),
            DetailAttachDocument("foto samping kanan", "00895.A1.png","HC7J+HFR, Kamojing, Kec. Cikampek, Karawang, Jawa Barat 41373")
        )
    }

    private fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter ?: return
        var totalHeight = 0
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            listItem.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            totalHeight += listItem.measuredHeight + 15 // Add 15 to each item's height
        }
        val params = listView.layoutParams
        params.height = totalHeight + (listView.dividerHeight * (listAdapter.count - 1))
        listView.layoutParams = params
    }


}
