package id.ac.polman.astra.afm004

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Models.LOVCostCenterDepreciation

class LOVCostCenterDeprectiationAdapter(
    private var costCenterDepreciations: List<LOVCostCenterDepreciation>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LOVCostCenterDeprectiationAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(costCenterDepreciation: LOVCostCenterDepreciation, notes: String)
    }

    inner class ViewHolder(itemView: View, viewNotes: View) : RecyclerView.ViewHolder(itemView) {
        val ccdNumber: TextView = itemView.findViewById(R.id.ccd_number)
        val ccdDescription: TextView = itemView.findViewById(R.id.ccd_description)
        val notes: EditText = viewNotes.findViewById(R.id.notes_cost_center_depreciation)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(costCenterDepreciations[position], notes.text.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_lov_cost_center_deprectiation, parent, false)
        val viewNotes = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_observation_asset, parent, false)
        return ViewHolder(view, viewNotes)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val costCenterDepreciation = costCenterDepreciations[position]
        holder.ccdNumber.text = costCenterDepreciation.ccd_number
        holder.ccdDescription.text = costCenterDepreciation.ccd_description
    }

    override fun getItemCount(): Int {
        return costCenterDepreciations.size
    }

    fun updateList(newList: List<LOVCostCenterDepreciation>) {
        costCenterDepreciations = newList
        notifyDataSetChanged()
    }
}