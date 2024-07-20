package id.ac.polman.astra.afm004

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Models.LOVAssetLocation

class LOVAssetLocationAdapter(
    private var lovAssetLocation: List<LOVAssetLocation>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LOVAssetLocationAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(assetLocation: LOVAssetLocation)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assetLocationNumber: TextView = itemView.findViewById(R.id.asset_location_number)
        val assetLocationDescription: TextView = itemView.findViewById(R.id.asset_location_description)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(lovAssetLocation[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_lov_asset_location, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assetLocation = lovAssetLocation[position]
        holder.assetLocationNumber.text = assetLocation.asset_location_number
        holder.assetLocationDescription.text = assetLocation.asset_location_description
    }

    override fun getItemCount() = lovAssetLocation.size

    fun updateList(newList: List<LOVAssetLocation>) {
        lovAssetLocation = newList
        notifyDataSetChanged()
    }
}