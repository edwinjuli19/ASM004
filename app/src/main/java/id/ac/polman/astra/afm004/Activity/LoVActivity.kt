package id.ac.polman.astra.afm004.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.ac.polman.astra.afm004.Fragment.*
import id.ac.polman.astra.afm004.Models.*
import id.ac.polman.astra.afm004.R

class LoVActivity : AppCompatActivity(),
    LOVAssetClassFragment.OnItemClickListener,
    LOVAssetGroupFragment.OnItemClickListener,
    LOVCostCenterDepreciationFragment.OnItemClickListener,
    LOVPlantIdFragment.OnItemClickListener,
    LOVActivityTypeFragment.OnItemClickListener,
    LOVAssetLocationFragment.OnItemClickListener,
    LOVAssetStatusFragment.OnItemClickListener,
    LOVDocumentTypeFragment.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lov)

        val searchEditText = findViewById<EditText>(R.id.search_view)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
                when (currentFragment) {
                    is LOVAssetClassFragment -> currentFragment.filterList(query)
                    is LOVAssetGroupFragment -> currentFragment.filterList(query)
                    is LOVCostCenterDepreciationFragment -> currentFragment.filterList(query)
                    is LOVPlantIdFragment -> currentFragment.filterList(query)
                    is LOVActivityTypeFragment -> currentFragment.filterList(query)
                    is LOVAssetLocationFragment -> currentFragment.filterList(query)
                    is LOVAssetStatusFragment -> currentFragment.filterList(query)
                    is LOVDocumentTypeFragment -> currentFragment.filterList(query)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        val arrowBack: TextView = findViewById(R.id.back)
        arrowBack.setOnClickListener {
            // Simply finish the current activity
            finish()
        }


        val fragmentType = intent.getStringExtra("fragment_type")
        val current_notes = intent.getStringExtra("current_notes")

        val fragment = when (fragmentType) {
            "asset_class" -> {
                LOVAssetClassFragment().apply {
                    arguments = Bundle().apply {
                        putString("current_notes", current_notes)
                    }
                }
            }
            "asset_group" -> {
                LOVAssetGroupFragment().apply {
                    arguments = Bundle().apply {
                        putString("current_notes", current_notes)
                    }
                }
            }
            "cost_center_depreciation" -> {
                LOVCostCenterDepreciationFragment().apply {
                    arguments = Bundle().apply {
                        putString("current_notes", current_notes)
                    }
                }
            }
            "plant_id" -> {
                LOVPlantIdFragment().apply {
                    arguments = Bundle().apply {
                        putString("current_notes", current_notes)
                    }
                }
            }

            "document_type_attach" -> LOVDocumentTypeFragment()

//            "activity_type" -> LOVActivityTypeFragment()
//            "asset_location" -> LOVAssetLocationFragment()
//            "asset_status" -> LOVAssetStatusFragment()
            else -> null
        }

        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, it)
                .commit()
        }
    }

    override fun onAssetClassSelected(assetClass: LOVAssetClass?,notes: String) {
        val resultIntent = Intent()
        if (assetClass != null) {
            resultIntent.putExtra("asset_class_number", assetClass.asset_class_number)
            resultIntent.putExtra("asset_class_description", assetClass.asset_class_description)
            resultIntent.putExtra("notes", notes)
        }

        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onAssetGroupSelected(assetGroup: LOVAssetGroup?, notes: String) {
        val resultIntent = Intent()
        if (assetGroup != null) {
            resultIntent.putExtra("asset_group_number", assetGroup.asset_group_number)
            resultIntent.putExtra("asset_group_description", assetGroup.asset_group_description)
            resultIntent.putExtra("notes", notes)
        }

        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onCostCenterDepreciationSelected(
        costCenterDepreciation: LOVCostCenterDepreciation?,
        notes: String
    ) {
        val resultIntent = Intent()
        if (costCenterDepreciation != null) {
            resultIntent.putExtra("ccd_number", costCenterDepreciation.ccd_number)
            resultIntent.putExtra("ccd_description", costCenterDepreciation.ccd_description)
            resultIntent.putExtra("notes", notes)
        }

        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onPlantIdSelected(plantId: LOVPlantId?, notes: String) {
        val resultIntent = Intent()
        if (plantId != null) {
            resultIntent.putExtra("plant_id_number", plantId.plant_id_number)
            resultIntent.putExtra("plant_id_description", plantId.plant_id_description)

            resultIntent.putExtra("notes", notes)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onActivityTypeSelected(activityType: LOVActivityType) {
        val resultIntent = Intent()
        resultIntent.putExtra("activity_type_number", activityType.activity_type_number)
        resultIntent.putExtra("activity_type_description", activityType.activity_type_description)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onAssetLocationSelected(assetLocation: LOVAssetLocation) {
        val resultIntent = Intent()
        resultIntent.putExtra("asset_location_number", assetLocation.asset_location_number)
        resultIntent.putExtra("asset_location_description", assetLocation.asset_location_description)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onAssetStatusSelected(assetStatus: LOVAssetStatus) {
        val resultIntent = Intent()
        resultIntent.putExtra("asset_status_description", assetStatus.asset_status_description)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    override fun onDocumentTypeSelected(documentType: LOVDocumentType) { // Add this method
        val resultIntent = Intent()
        resultIntent.putExtra("asset_type", documentType.asset_type)
        resultIntent.putExtra("document_type", documentType.document_type)
        setResult(RESULT_OK, resultIntent)
        finish()
    }


}
