package id.ac.polman.astra.afm004

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Activity.ObservationActivity

class ObservationAdapter(private val observationList: List<HistoryObservation>) :
    RecyclerView.Adapter<ObservationAdapter.ViewHolder>() {

    // Interface untuk listener
    interface OnItemClickListener {
        fun onItemClick(historyObservation: HistoryObservation)
    }

    private var listener: OnItemClickListener? = null


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val observationName: TextView = itemView.findViewById(R.id.observation_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(observationList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_observation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val observation = observationList[position]
        holder.observationName.text = observation.name

        holder.itemView.setOnClickListener {
            // Pindah ke ObservationActivity
            val context = holder.itemView.context
            val intent = Intent(context, ObservationActivity::class.java)
            // Anda bisa menambahkan data tambahan ke intent jika diperlukan
            intent.putExtra("observationName", observation.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = observationList.size
}
