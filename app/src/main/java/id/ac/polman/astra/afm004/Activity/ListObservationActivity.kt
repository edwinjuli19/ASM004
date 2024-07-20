package id.ac.polman.astra.afm004.Activity

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.ac.polman.astra.afm004.Fragment.HistoryObservationFragment
import id.ac.polman.astra.afm004.Fragment.ListObservationFragment
import id.ac.polman.astra.afm004.R

class ListObservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_observation)

        val searchEditText = findViewById<EditText>(R.id.search)
        searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Perform search action here
                val query = searchEditText.text.toString()
                // TODO: Handle the search action

                // Hide the keyboard
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                return@OnEditorActionListener true
            }
            false
        })

        val listObservationTextView = findViewById<TextView>(R.id.list_observation)
        val historyObservationTextView = findViewById<TextView>(R.id.history_observation)

        listObservationTextView.setOnClickListener {
            replaceFragment(ListObservationFragment())
        }

        historyObservationTextView.setOnClickListener {
            replaceFragment(HistoryObservationFragment())
        }

        // Load the default fragment
        replaceFragment(ListObservationFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}


//    private lateinit var permissionHandler: PermissionHandler
//    private lateinit var locationHandler: LocationHandler
//    private lateinit var imageHandler: ImageHandler
//    private lateinit var tvLocation: TextView
//    private lateinit var ivPicture: ImageView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.list_request_settlement)
//
//
//        // Inisialisasi Handlers
//        permissionHandler = PermissionHandler(this)
//        locationHandler = LocationHandler(this, tvLocation)
//        imageHandler = ImageHandler(this, ivPicture, tvLocation)
//
//        // Setup Status Bar
//        setupStatusBar()
//
//        // Periksa dan Minta Izin
//        if (permissionHandler.checkAndRequestPermissions()) {
//            // Izin telah diberikan, ambil lokasi terakhir
//            locationHandler.getLastLocation()
//        }
//    }
//
//    private fun setupStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.statusBarColor = Color.WHITE
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (permissionHandler.onRequestPermissionsResult(requestCode, grantResults)) {
//            // Izin telah diberikan, ambil lokasi terakhir
//            locationHandler.getLastLocation()
//        } else {
//            // Tangani kasus ketika izin tidak diberikan
//            tvLocation.text = "Permissions not granted"
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        // Tangani hasil aktivitas untuk ImageHandler
//        imageHandler.handleActivityResult(requestCode, resultCode, data, locationHandler)
//    }

