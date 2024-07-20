package id.ac.polman.astra.afm004

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Models.LOVActivityType

class LOVActivityTypeAdapter(
    private var lovActivityType: List<LOVActivityType>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LOVActivityTypeAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(activityType: LOVActivityType)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activityTypeNumber: TextView = itemView.findViewById(R.id.activity_type_number)
        val activityTypeDescription: TextView = itemView.findViewById(R.id.activity_type_description)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(lovActivityType[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_lov_activity_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activityType = lovActivityType[position]
        holder.activityTypeNumber.text = activityType.activity_type_number
        holder.activityTypeDescription.text = activityType.activity_type_description
    }

    override fun getItemCount() = lovActivityType.size

    fun updateList(newList: List<LOVActivityType>) {
        lovActivityType = newList
        notifyDataSetChanged()
    }
}