package id.ac.polman.astra.afm004

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import id.ac.polman.astra.afm004.Models.DetailAttachDocument
import id.ac.polman.astra.afm004.R

class DetailAttachDocumentAdapter(context: Context, private val detaildoc: List<DetailAttachDocument>) :
    ArrayAdapter<DetailAttachDocument>(context, 0, detaildoc) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.card_attach_file, parent, false)
        val document = detaildoc[position]

        val documentType: TextView = view.findViewById(R.id.document_type)
        val documentName: TextView = view.findViewById(R.id.document_name)
        val geotag: TextView = view.findViewById(R.id.geotag)

        documentType.text = document.document_type
        documentName.text = document.document_name
        geotag.text = document.geotag

        return view
    }
}
