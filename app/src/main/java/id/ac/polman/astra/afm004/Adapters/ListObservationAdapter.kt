package id.ac.polman.astra.afm004

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import id.ac.polman.astra.afm004.Activity.ObservationActivity

class ListObservationAdapter(private val observationList: List<ListObservation>) :
    RecyclerView.Adapter<ListObservationAdapter.ViewHolder>() {

    // Interface untuk listener
    interface OnItemClickListener {
        fun onItemClick(listObservation: ListObservation)
    }

    private var listener: ListObservationAdapter.OnItemClickListener? = null

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
            .inflate(R.layout.card_settelment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListObservationAdapter.ViewHolder, position: Int) {
        val observation = observationList[position]
        holder.observationName.text = observation.observationName

        holder.itemView.setOnClickListener {
            // Pindah ke ObservationActivity
            val context = holder.itemView.context
            val intent = Intent(context, ObservationActivity::class.java)
            // Anda bisa menambahkan data tambahan ke intent jika diperlukan
            intent.putExtra("observationName", observation.observationName)
            intent.putExtra("observationStatus", "WAITING")
            context.startActivity(intent)

            if (context is AppCompatActivity) {
                context.overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
            }
        }
    }

    override fun getItemCount() = observationList.size
}
