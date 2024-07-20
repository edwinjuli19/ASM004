package id.ac.polman.astra.afm004

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Models.LOVAssetClass

class LOVAssetClassAdapter(
    private var assetClasses: List<LOVAssetClass>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LOVAssetClassAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(assetClass: LOVAssetClass, notes: String)
    }

    inner class ViewHolder(itemView: View,ViewNotes: View) : RecyclerView.ViewHolder(itemView) {
        val assetClassNumber: TextView = itemView.findViewById(R.id.asset_class_number)
        val assetClassDescription: TextView = itemView.findViewById(R.id.asset_class_description)

        val notes: EditText = ViewNotes.findViewById(R.id.notes_asset_class)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(assetClasses[position], notes.text.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_lov_asset_class, parent, false)
        val viewnotes = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_observation_asset, parent, false)
        return ViewHolder(view,viewnotes)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assetClass = assetClasses[position]
        holder.assetClassNumber.text = assetClass.asset_class_number
        holder.assetClassDescription.text = assetClass.asset_class_description
    }

    override fun getItemCount(): Int {
        return assetClasses.size
    }

    fun updateList(newList: List<LOVAssetClass>) {
        assetClasses = newList
        notifyDataSetChanged()
    }
}