package id.ac.polman.astra.afm004

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Models.LOVAssetGroup

class LOVAssetGroupAdapter(
    private var assetGroups: List<LOVAssetGroup>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LOVAssetGroupAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(assetGroup: LOVAssetGroup, notes: String)
    }

    inner class ViewHolder(itemView: View, viewNotes: View) : RecyclerView.ViewHolder(itemView) {
        val asset_group_number: TextView = itemView.findViewById(R.id.asset_group_number)
        val asset_group_description: TextView = itemView.findViewById(R.id.asset_group_description)

        val notes: EditText = viewNotes.findViewById(R.id.notes_asset_group)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(assetGroups[position], notes.text.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_lov_asset_group, parent, false)
        val viewNotes = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_observation_asset, parent, false)
        return ViewHolder(view, viewNotes)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assetGroup = assetGroups[position]
        holder.asset_group_number.text = assetGroup.asset_group_number
        holder.asset_group_description.text = assetGroup.asset_group_description
    }

    override fun getItemCount() = assetGroups.size

    fun updateList(newList: List<LOVAssetGroup>) {
        assetGroups = newList
        notifyDataSetChanged()
    }
}