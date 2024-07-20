package id.ac.polman.astra.afm004

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Activity.ObservationActivity

class HistoryObservationAdapter(private val observationList: List<HistoryObservation>) :
    RecyclerView.Adapter<HistoryObservationAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val observationName: TextView = itemView.findViewById(R.id.observation_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val observation = observationList[position]
                    val context = itemView.context
                    val intent = Intent(context, ObservationActivity::class.java)
                    intent.putExtra("observationName", observation.name)
                    context.startActivity(intent)

                    if (context is AppCompatActivity) {
                        context.overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
                    }
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
    }

    override fun getItemCount() = observationList.size
}
