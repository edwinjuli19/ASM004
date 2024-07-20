package id.ac.polman.astra.afm004.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import id.ac.polman.astra.afm004.R

class ViewObservationPendingFragment : Fragment() {

    private lateinit var item1: EditText
    private lateinit var item2: EditText
    private lateinit var item3: EditText
    private lateinit var item4: EditText
    private lateinit var item6: EditText
    private lateinit var item7: EditText
    private lateinit var btnObservation: Button
    private lateinit var btnBack: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_observation_pending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize UI components
        item1 = view.findViewById(R.id.item1)
        item2 = view.findViewById(R.id.item2)
        item3 = view.findViewById(R.id.item3)
        item4 = view.findViewById(R.id.item4)
        item6 = view.findViewById(R.id.item6)
        item7 = view.findViewById(R.id.item7)
        btnObservation = view.findViewById(R.id.btn_observation)
        btnBack = view.findViewById(R.id.btn_back)

        // Set up button listeners
        btnObservation.setOnClickListener {
            // Handle observation button click
            handleObservationClick()
        }

        btnBack.setOnClickListener {
            // Handle back button click
            handleBackClick()
        }
    }

    private fun handleObservationClick() {
        // Logic for handling observation button click
    }

    private fun handleBackClick() {
        // Logic for handling back button click
        activity?.onBackPressed() // Example: go back to previous activity/fragment
    }
}
