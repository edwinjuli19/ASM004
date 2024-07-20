package id.ac.polman.astra.ObservationSettlement.main

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.exifinterface.media.ExifInterface

class ImageHandler(private val activity: Activity, private val ivPicture: ImageView, private val tvLocation: TextView) {

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 101
        const val REQUEST_IMAGE_SELECT = 102
    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?, locationHandler: LocationHandler) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val pic: Bitmap? = data?.getParcelableExtra("data")
            ivPicture.setImageBitmap(pic)
            locationHandler.getLastLocation()  // Ensure location is fetched again
            locationHandler.getCurrentLocation()?.let {
                locationHandler.updateLocationText(it.latitude, it.longitude)
            }
        } else if (requestCode == REQUEST_IMAGE_SELECT && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            if (imageUri != null) {
                try {
                    val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(activity.contentResolver, imageUri)
                    ivPicture.setImageBitmap(bitmap)
                    displayImageLocation(imageUri, locationHandler)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun displayImageLocation(imageUri: Uri, locationHandler: LocationHandler) {
        try {
            val inputStream = activity.contentResolver.openInputStream(imageUri)
            if (inputStream != null) {
                val exifInterface = ExifInterface(inputStream)
                val latitude = exifInterface.latLong?.firstOrNull()
                val longitude = exifInterface.latLong?.lastOrNull()

                if (latitude != null && longitude != null) {
                    locationHandler.updateLocationText(latitude, longitude)
                } else {
                    tvLocation.text = "Location data not available"
                }
            } else {
                tvLocation.text = "Unable to open file"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            tvLocation.text = "Error reading file"
        }
    }
}
