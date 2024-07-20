package id.ac.polman.astra.afm004

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Models.LOVDocumentType

class LOVDocumentTypeAdapter(
    private var documentTypes: List<LOVDocumentType>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LOVDocumentTypeAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(documentType: LOVDocumentType)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assetType: TextView = itemView.findViewById(R.id.asset_type)
        val documentType: TextView = itemView.findViewById(R.id.document_type)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(documentTypes[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_lov_document_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val documentType = documentTypes[position]
        holder.assetType.text = documentType.asset_type
        holder.documentType.text = documentType.document_type
    }

    override fun getItemCount(): Int {
        return documentTypes.size
    }

    fun updateList(newList: List<LOVDocumentType>) {
        documentTypes = newList
        notifyDataSetChanged()
    }
}
