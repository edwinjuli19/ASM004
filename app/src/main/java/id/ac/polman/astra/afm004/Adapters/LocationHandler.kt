package id.ac.polman.astra.ObservationSettlement.main

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class LocationHandler(private val activity: Activity, private val tvLocation: TextView) {

    private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
    private var currentLocation: Location? = null

    fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    currentLocation = location
                    updateLocationText(location.latitude, location.longitude)
                } else {
                    tvLocation.text = "Unable to get location"
                }
            }
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        }
    }

    fun updateLocationText(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(activity, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        if (!addresses.isNullOrEmpty()) {
            val address = addresses[0]
            val locationText = "${address.featureName}, ${address.locality}, ${address.subAdminArea}, ${address.adminArea} ${address.postalCode}"
            tvLocation.text = locationText
        } else {
            tvLocation.text = "Unable to get address"
        }
    }

    fun getCurrentLocation(): Location? {
        return currentLocation
    }
}
