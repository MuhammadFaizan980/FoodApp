package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
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


    override fun setCurrentLocation(map: GoogleMap) {
        val locationProvider = LocationServices.getFusedLocationProviderClient(context)
        locationProvider.lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val location: Location = task.result!!
                map.isMyLocationEnabled = true
                map.uiSettings.isMyLocationButtonEnabled = true
                val latLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            } else {
                Toast.makeText(context, task.exception!!.message!!, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
            || ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            val permissionArray = arrayOfNulls<String>(2)
            permissionArray[0] = android.Manifest.permission.ACCESS_FINE_LOCATION
            permissionArray[1] = android.Manifest.permission.ACCESS_COARSE_LOCATION
            ActivityCompat.requestPermissions(mActivity, permissionArray, 99)
            mView.onPerssionsResult(false)
        } else {
            mView.onPerssionsResult(true)
        }
    }
}