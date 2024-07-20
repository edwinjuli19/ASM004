package id.ac.polman.astra.afm004

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Models.LOVPlantId

class LOVPlantIdAdapter(
    private var plantIds: List<LOVPlantId>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LOVPlantIdAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(plantId: LOVPlantId)
    }

    inner class ViewHolder(itemView: View, viewNotes: View) : RecyclerView.ViewHolder(itemView) {
        val plantIdNumber: TextView = itemView.findViewById(R.id.plantid_number)
        val plantIdDescription: TextView = itemView.findViewById(R.id.plantid_description)
        val notes: EditText = viewNotes.findViewById(R.id.notes_plant_id)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(plantIds[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_lov_plant_id, parent, false)
        val viewNotes = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_observation_asset, parent, false)
        return ViewHolder(view, viewNotes)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plantId = plantIds[position]
        holder.plantIdNumber.text = plantId.plant_id_number
        holder.plantIdDescription.text = plantId.plant_id_description
    }

    override fun getItemCount(): Int {
        return plantIds.size
    }

    fun updateList(newList: List<LOVPlantId>) {
        plantIds = newList
        notifyDataSetChanged()
    }
}