package id.ac.polman.astra.afm004.Fragment

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import id.ac.polman.astra.ObservationSettlement.main.ImageHandler
import id.ac.polman.astra.ObservationSettlement.main.LocationHandler
import id.ac.polman.astra.ObservationSettlement.main.PermissionHandler
import id.ac.polman.astra.afm004.Activity.LoVActivity
import id.ac.polman.astra.afm004.R
import java.util.Calendar

class ViewObservationWaitingFragment : Fragment(), NotesFragment.OnNotesSavedListener {

    private lateinit var lovAssetClass: EditText
    private lateinit var lovAssetGroup: EditText
    private lateinit var lovCostCenterDepreciation: EditText
    private lateinit var lovPlantId: EditText
    private lateinit var lovActivityType: EditText
    private lateinit var lovAssetLocation: EditText
    private lateinit var lovAssetStatus: EditText
    private lateinit var assetDescription: EditText
    private lateinit var editAssetDescription: Button
    private lateinit var notesAssetDescription: EditText
    private lateinit var pendingObservation: Button
    private lateinit var notesPendingObservation: EditText
    private lateinit var notesAssetClass: EditText
    private lateinit var notesAssetGroup: EditText
    private lateinit var notesCostCenterDepreciation: EditText
    private lateinit var notesPlantId: EditText

    private lateinit var assetSudahDigunakanEditText: EditText
    private lateinit var updateToExpenseEditText: EditText
    private lateinit var updateToSubsetEditText: EditText
    private lateinit var partialSettlementEditText: EditText
    private lateinit var splitAssetEditText: EditText
    private lateinit var tanggalMasProEditText: EditText
    private lateinit var assetExportLicense: EditText
    private lateinit var assetManufacturing: EditText
    private lateinit var btnReadyToSettle: Button

    private lateinit var attachButton: ImageView
    private lateinit var documentTypeAttach: LinearLayout
    private lateinit var textJenis: TextView
    private lateinit var text_document_location: TextView

    // Handlers
    private lateinit var imageHandler: ImageHandler
    private lateinit var locationHandler: LocationHandler
    private lateinit var permissionHandler: PermissionHandler


    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 101
        private const val REQUEST_IMAGE_SELECT = 102
        private const val REQUEST_CAMERA_PERMISSION = 103
        private val PICK_IMAGE_REQUEST = 1
        private val PICK_DOCUMENT_REQUEST = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_observation_asset, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //geotag
        text_document_location = view.findViewById(R.id.text_document_location)

        // Initialize handlers
        imageHandler = ImageHandler(requireActivity(), view.findViewById(R.id.iv_picture), text_document_location)
        locationHandler = LocationHandler(requireActivity(), text_document_location)
        permissionHandler = PermissionHandler(requireActivity())

        attachButton = view.findViewById(R.id.ic_cht_attach)

        assetSudahDigunakanEditText = view.findViewById(R.id.Assetsudahdigunakan)
        updateToExpenseEditText = view.findViewById(R.id.UpdateToExpense)
        updateToSubsetEditText = view.findViewById(R.id.UpdatetoSubset)
        partialSettlementEditText = view.findViewById(R.id.PartialSettlement)
        splitAssetEditText = view.findViewById(R.id.SplitAsset)
        tanggalMasProEditText = view.findViewById(R.id.TanggalMasPro)
        assetExportLicense = view.findViewById(R.id.asset_export_license)
        assetManufacturing = view.findViewById(R.id.asset_manufacturing)
        btnReadyToSettle = view.findViewById(R.id.btn_readytosettle)
        documentTypeAttach = view.findViewById(R.id.document_type_attach)

        assetSudahDigunakanEditText.setOnClickListener { showPopupMenu(it) }
        updateToExpenseEditText.setOnClickListener { showPopupMenu(it) }
        updateToSubsetEditText.setOnClickListener { showPopupMenu(it) }
        partialSettlementEditText.setOnClickListener { showPopupMenu(it) }
        splitAssetEditText.setOnClickListener { showPopupMenu(it) }
        tanggalMasProEditText.setOnClickListener { showDatePickerDialog() }

        assetExportLicense.setOnClickListener { showPopupMenu(it) }
        assetManufacturing.setOnClickListener { showAssetManufacturing(it) }

        // Initialize UI components
        assetDescription = view.findViewById(R.id.asset_description)
        editAssetDescription = view.findViewById(R.id.btn_edit_asset_description)
        notesAssetDescription = view.findViewById(R.id.notes_asset_description)
        notesAssetClass = view.findViewById(R.id.notes_asset_class)
        notesAssetGroup = view.findViewById(R.id.notes_asset_group)
        notesCostCenterDepreciation = view.findViewById(R.id.notes_cost_center_depreciation)
        notesPlantId = view.findViewById(R.id.notes_plant_id)

        lovAssetClass = view.findViewById(R.id.asset_class)
        lovAssetClass.setOnClickListener {
            startLoVActivity("asset_class", notesAssetClass.text.toString())
        }

        lovAssetGroup = view.findViewById(R.id.asset_group)
        lovAssetGroup.setOnClickListener {
            startLoVActivity("asset_group", notesAssetGroup.text.toString())
        }

        lovCostCenterDepreciation = view.findViewById(R.id.cost_center_depreciation)
        lovCostCenterDepreciation.setOnClickListener {
            startLoVActivity("cost_center_depreciation", notesCostCenterDepreciation.text.toString())
        }

        lovPlantId = view.findViewById(R.id.plant_id)
        lovPlantId.setOnClickListener {
            startLoVActivity("plant_id", notesPlantId.text.toString())
        }


        lovActivityType = view.findViewById(R.id.activity_type)
        lovActivityType.setOnClickListener {
//            startLoVActivity("activity_type")
        }

        lovAssetLocation = view.findViewById(R.id.asset_location)
        lovAssetLocation.setOnClickListener {
//            startLoVActivity("asset_location")
        }

        lovAssetStatus = view.findViewById(R.id.asset_status)
        lovAssetStatus.setOnClickListener {
//            startLoVActivity("asset_status")
        }

        documentTypeAttach = view.findViewById(R.id.document_type_attach)
        textJenis = view.findViewById(R.id.text_jenis)
        documentTypeAttach.setOnClickListener {
            startLoVActivity("document_type_attach", "")
        }



        // Set up button listeners
        editAssetDescription.setOnClickListener {
            val buttonText = editAssetDescription.text.toString()
            Log.d("editSaveButton", buttonText)

            // Get the value from notesAssetDescription EditText
            val valueAssetDescriptionNotes = notesAssetDescription.text.toString()
            Log.d("editSaveButton", "Notes: $valueAssetDescriptionNotes")

            if (buttonText == "Edit") {
                // Enable EditText for editing
                assetDescription.isCursorVisible = true
                assetDescription.isFocusable = true
                assetDescription.isFocusableInTouchMode = true
                assetDescription.isLongClickable = true
                assetDescription.isEnabled = true

                // Change button text to "Save"
                editAssetDescription.text = "Save"
                editAssetDescription.setBackgroundColor(resources.getColor(R.color.dark_red))
            } else if (buttonText == "Save") {
                // Show fragment for notes
                showNotesFragment(notesAssetDescription.text.toString(), "asset_description")
            }
        }

        notesPendingObservation = view.findViewById(R.id.notes_pending_observation)
        pendingObservation = view.findViewById(R.id.btn_pending);
        pendingObservation.setOnClickListener {
            showNotesFragment(notesPendingObservation.text.toString(), "pending_observation")
        }

        notesAssetClass = view.findViewById(R.id.notes_asset_class)

        btnReadyToSettle.setOnClickListener {
            showSuccessDialog()
        }

        attachButton.setOnClickListener {
            showUploadOptions()
        }


        val detailAttachDocumentFragment = DetailAttachDocumentFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_file, detailAttachDocumentFragment)
            .commitAllowingStateLoss()


    }

    private fun showNotesFragment(notes: String, notes_type: String) {
        // Create and show your fragment for notes input
        val notesFragment = NotesFragment()
        notesFragment.setTargetFragment(this, 0) // Set target fragment
        notesFragment.setOnNotesSavedListener(this)

        // Create a bundle to pass the current value of assetDescription
        val args = Bundle()
        args.putString("current_notes", notes)
        args.putString("notes_type", notes_type)
        notesFragment.arguments = args

        // Show the fragment
        notesFragment.show(parentFragmentManager, "NotesFragment")
    }

    override fun onNotesSaved(notes: String, notes_type: String) {
        Log.d("onNotesSaved", notes)
        Log.d("onNotesSaved", notes_type)
        if(notes_type == "asset_description") {
            // Close the fragment and update the EditText
            notesAssetDescription.setText(notes)

            // Disable EditText again
            assetDescription.isCursorVisible = false
            assetDescription.isFocusable = false
            assetDescription.isFocusableInTouchMode = false
            assetDescription.isLongClickable = false
            assetDescription.isEnabled = false

            // Change button text back to "Edit" and color to the original
            editAssetDescription.text = "Edit"
            editAssetDescription.setBackgroundColor(resources.getColor(R.color.green))
        }else if(notes_type == "pending_observation") {
            // Close the fragment and update the EditText
            notesPendingObservation.setText(notes)
        }
    }

    private fun startLoVActivity(fragmentType: String, notes: String) {
        val intent = Intent(requireContext(), LoVActivity::class.java)
        intent.putExtra("fragment_type", fragmentType)
        intent.putExtra("current_notes", notes)
        Log.d("current_notes", notes)
        startActivityForResult(intent, getRequestCode(fragmentType))
        requireActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && data != null) {
            when (requestCode) {
                getRequestCode("asset_class") -> {
                    val assetClassNumber = data.getStringExtra("asset_class_number") ?: ""
                    val assetClassDescription = data.getStringExtra("asset_class_description") ?: ""
                    val notes = data.getStringExtra("notes") ?: ""

                    lovAssetClass.setText("$assetClassNumber - $assetClassDescription")
                    notesAssetClass.setText(notes)
                }
                getRequestCode("asset_group") -> {
                    val assetGroupNumber = data.getStringExtra("asset_group_number") ?: ""
                    val assetGroupDescription = data.getStringExtra("asset_group_description") ?: ""
                    val notes = data.getStringExtra("notes") ?: ""

                    lovAssetGroup.setText("$assetGroupNumber - $assetGroupDescription")
                    notesAssetGroup.setText(notes)
                }
                getRequestCode("cost_center_depreciation") -> {
                    val ccdNumber = data.getStringExtra("ccd_number") ?: ""
                    val ccdDescription = data.getStringExtra("ccd_description") ?: ""
                    val notes = data.getStringExtra("notes") ?: ""

                    lovCostCenterDepreciation.setText("$ccdNumber - $ccdDescription")
                    notesCostCenterDepreciation.setText(notes)
                }
                getRequestCode("plant_id") -> {
                    val plantIdNumber = data.getStringExtra("plant_id_number") ?: ""
                    val plantIdDescription = data.getStringExtra("plant_id_description") ?: ""
                    val notes = data.getStringExtra("notes") ?: ""

                    Log.d("plantIdNumber", plantIdNumber)
                    Log.d("plantIdNumber", notes)
                    lovPlantId.setText("$plantIdNumber - $plantIdDescription")
                    notesPlantId.setText(notes)
                }
                getRequestCode("activity_type") -> {
                    val activityTypeNumber = data.getStringExtra("activity_type_number") ?: ""
                    val activityTypeDescription = data.getStringExtra("activity_type_description") ?: ""
                    lovActivityType.setText("$activityTypeNumber - $activityTypeDescription")
                }
                getRequestCode("asset_location") -> {
                    val assetLocationNumber = data.getStringExtra("asset_location_number") ?: ""
                    val assetLocationDescription = data.getStringExtra("asset_location_description") ?: ""
                    lovAssetLocation.setText("$assetLocationNumber - $assetLocationDescription")
                }
                getRequestCode("asset_status") -> {
                    val assetStatusDescription = data.getStringExtra("asset_status_description") ?: ""
                    lovAssetStatus.setText("$assetStatusDescription")
                }

                getRequestCode("document_type_attach") -> {
                    val assetType = data.getStringExtra("asset_type") ?: ""
                    val documentType = data.getStringExtra("document_type") ?: ""
                    textJenis.text = ("$assetType - $documentType")
                }
            }
        }


    }

    private fun validateEditTexts(changedEditText: EditText) {
        if (changedEditText.text.toString() == "Yes") {
            when (changedEditText.id) {
                R.id.Assetsudahdigunakan -> {
                    updateToExpenseEditText.setText("No")
                    updateToSubsetEditText.setText("No")
                    partialSettlementEditText.setText("No")
                    splitAssetEditText.setText("No")
                }
                R.id.UpdateToExpense -> {
                    assetSudahDigunakanEditText.setText("No")
                    updateToSubsetEditText.setText("No")
                    partialSettlementEditText.setText("No")
                    splitAssetEditText.setText("No")
                }
                R.id.UpdatetoSubset -> {
                    assetSudahDigunakanEditText.setText("No")
                    updateToExpenseEditText.setText("No")
                    partialSettlementEditText.setText("No")
                    splitAssetEditText.setText("No")
                }
                R.id.PartialSettlement -> {
                    assetSudahDigunakanEditText.setText("No")
                    updateToExpenseEditText.setText("No")
                    updateToSubsetEditText.setText("No")
                    splitAssetEditText.setText("No")
                }
                R.id.SplitAsset -> {
                    assetSudahDigunakanEditText.setText("No")
                    updateToExpenseEditText.setText("No")
                    updateToSubsetEditText.setText("No")
                    partialSettlementEditText.setText("No")
                }
            }
        }
    }


    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            val editText = view as EditText
            editText.setText(item.title)
            validateEditTexts(editText)
            true
        }

        popupMenu.show()
    }

    private fun showAssetManufacturing(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.asset_manufacturing, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            val editText = view as EditText
            editText.setText(item.title)
            validateEditTexts(editText)
            true
        }

        popupMenu.show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val date = String.format("%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear)
                tanggalMasProEditText.setText(date)
            },
            year, month, day
        )

        datePickerDialog.show()
    }

    private fun getRequestCode(fragmentType: String): Int {
        // Example mapping fragmentType to unique request codes
        return when (fragmentType) {
            "asset_class" -> 1
            "asset_group" -> 2
            "cost_center_depreciation" -> 3
            "plant_id" -> 4
            "activity_type" -> 5
            "asset_location" -> 6
            "asset_status" -> 7
            "document_type_attach" -> 8
            else -> 0
        }
    }


    //Detail Document

    private fun showUploadOptions() {
        val options = arrayOf("Take Photo", "Choose from Gallery")
        val builder = android.app.AlertDialog.Builder(requireContext())
        builder.setTitle("Select an option")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> checkCameraPermission()
                1 -> openGallery()
            }
        }
        builder.show()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
        } else {
            openCamera()
        }
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun openGallery() {
        val pickPhotoIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_SELECT)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                openCamera()
            } else {
                // Permission denied
            }
        }
    }

    private fun showSuccessDialog() {
        val successDialog = SuccessDialogFragment()
        successDialog.show(parentFragmentManager, "successDialog")
    }

    private fun getFileName(uri: Uri?): String {
        // Implement logic to extract file name from Uri
        return uri?.lastPathSegment ?: "Unknown"
    }

    private fun getGeotag(): String {
        // Implement logic to get geotag, if applicable
        return "Geotag"
    }


}