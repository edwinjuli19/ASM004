package id.ac.polman.astra.afm004.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import id.ac.polman.astra.afm004.R
import id.ac.polman.astra.afm004.Models.AssetObservation

class ViewObservationFragment : Fragment() {

    private lateinit var aucNumber: EditText
    private lateinit var assetDescription: EditText
    private lateinit var asetClass: EditText
    private lateinit var assetGroup: EditText
    private lateinit var costCenterAssetResponsible: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_observation_asset, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aucNumber = view.findViewById(R.id.auc_number)
        assetDescription = view.findViewById(R.id.asset_description)
        asetClass = view.findViewById(R.id.aset_class)
        assetGroup = view.findViewById(R.id.asset_group)
        costCenterAssetResponsible = view.findViewById(R.id.cost_center_asset_responsible)

        // Populate the EditText fields with data
        val assetObservation = getAssetObservation()
        aucNumber.setText(assetObservation.auc_number)
        assetDescription.setText(assetObservation.asset_description)
        asetClass.setText(assetObservation.aset_class)
        assetGroup.setText(assetObservation.asset_group)
        costCenterAssetResponsible.setText(assetObservation.cost_center_asset_responsible)
    }

    private fun getAssetObservation(): AssetObservation {
        // Replace this with actual data fetching logic
        return AssetObservation(
            auc_number = "200000022921 - 0",
            asset_description = "Auto Loader Press Cone Race",
            aset_class = "10500 - Fixed Asset - Machineries",
            asset_group = "10500012 - Conveyor",
            cost_center_asset_responsible = "Auto Loader Press Cone Race"
        )
    }
}