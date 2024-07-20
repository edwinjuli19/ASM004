package id.ac.polman.astra.afm004

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Models.AssetObservation

class ViewObservationAdapter(private val viewobservation: List<AssetObservation>) :
    RecyclerView.Adapter<ViewObservationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val auc_number: TextView = itemView.findViewById(R.id.auc_number)
        val asset_description: TextView = itemView.findViewById(R.id.asset_status_description)
        val aset_class: TextView = itemView.findViewById(R.id.asset_status_description)
        val asset_group: TextView = itemView.findViewById(R.id.asset_status_description)
        val cost_center_asset_responsible: TextView = itemView.findViewById(R.id.asset_status_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_lov_asset_status, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.auc_number.text = viewobservation[position].auc_number
        holder.asset_description.text = viewobservation[position].asset_description
        holder.aset_class.text = viewobservation[position].aset_class
        holder.asset_group.text = viewobservation[position].asset_group
        holder.cost_center_asset_responsible.text = viewobservation[position].cost_center_asset_responsible
    }

    override fun getItemCount() = viewobservation.size
}
