package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

class AroundMePresenter(
    private val mView: AroundMeContracts.IView,
    private val context: Context,
    private val mActivity: Activity
) :
    AroundMeContracts.IPresenter {


    @SuppressLint("MissingPermission")
    override fun setCurrentLocation(map: GoogleMap) {
        val locationProvider = LocationServices.getFusedLocationProviderClient(context)
        locationProvider.lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                try {
                    val location: Location = task.result!!
                    map.isMyLocationEnabled = true
                    map.uiSettings.isMyLocationButtonEnabled = true
                    val latLng = LatLng(location.latitude, location.longitude)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                } catch (exc: Exception) {
                    showErrorDialog()
                }
            } else {
                Toast.makeText(
                    context,
                    "There was an error getting your location",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showErrorDialog() {
        val dialog = AlertDialog.Builder(mActivity)
        dialog.setTitle("Message!")
        dialog.setMessage("Location currently not available\nTry high accuracy mode?")
        dialog.setCancelable(false)
        dialog.setPositiveButton("Yes") { dialogInterface, i ->
            context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            mActivity.finish()
        }.setNegativeButton("Cancel") { dialogInterface, i ->
            dialogInterface.cancel()
        }
        dialog.show()
    }

}