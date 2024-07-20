package id.ac.polman.astra.afm004.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.ac.polman.astra.afm004.Fragment.ViewObservationOkFragment
import id.ac.polman.astra.afm004.Fragment.ViewObservationPendingFragment
import id.ac.polman.astra.afm004.Fragment.ViewObservationWaitingFragment
import id.ac.polman.astra.afm004.R

class ObservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observation)

        val observationStatus = intent.getStringExtra("observationStatus")

        // Replace fragment based on observationStatus
        if (observationStatus == "PENDING") {
            replaceFragment(ViewObservationPendingFragment())
        } else if (observationStatus == "OBSERVATION OK") {
            replaceFragment(ViewObservationOkFragment())
        } else if (observationStatus == "WAITING") {
            replaceFragment(ViewObservationWaitingFragment())
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}