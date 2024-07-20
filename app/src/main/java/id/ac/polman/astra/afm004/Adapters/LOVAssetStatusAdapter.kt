package id.ac.polman.astra.afm004

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Models.LOVAssetStatus

class LOVAssetStatusAdapter(
    private var lovAssetStatus: List<LOVAssetStatus>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LOVAssetStatusAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(assetStatus: LOVAssetStatus)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assetStatusDescription: TextView = itemView.findViewById(R.id.asset_status_description)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(lovAssetStatus[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_lov_asset_status, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assetStatus = lovAssetStatus[position]
        holder.assetStatusDescription.text = assetStatus.asset_status_description
    }

    override fun getItemCount() = lovAssetStatus.size

    fun updateList(newList: List<LOVAssetStatus>) {
        lovAssetStatus = newList
        notifyDataSetChanged()
    }
}