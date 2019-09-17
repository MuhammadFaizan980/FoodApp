package com.squadtechs.markhor.foodapp.customer.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.location.Location
import android.provider.Settings
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.squadtechs.markhor.foodapp.customer.Fragments.fragment_inside_food.InsideFoodContracts

class CustomerUtils {
    companion object {
        fun decodeCoordinates(address: String): LatLng {
            var lat: String = ""
            var lng: String = ""
            var flag = true

            for (i in address) {
                if (i.equals(',') || i.equals(' ')) {
                    flag = false
                    continue
                }
                if (flag) {
                    lat += i
                } else {
                    lng += i
                }
            }
            return LatLng(lat.toDouble(), lng.toDouble())
        }

        fun getCurrentLocation(
            mView: InsideFoodContracts?,
            locationProvider: FusedLocationProviderClient,
            mActivity: Activity
        ) {
            var location: Location? = null
            locationProvider.lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    location = task.result
                    if (location != null) {
                        val pref = mActivity.getSharedPreferences(
                            "customer_current_location",
                            Context.MODE_PRIVATE
                        )
                        val editor = pref.edit()
                        editor.putString("lat", location!!.latitude.toString())
                        editor.putString("lng", location!!.longitude.toString())
                        editor.apply()
                        mView?.onGetLocationResponse(true, location)
                    } else {
                        mView?.onGetLocationResponse(false, null)
                    }
                } else {
                    Toast.makeText(
                        mActivity,
                        "There was an error getting your location",
                        Toast.LENGTH_LONG
                    ).show()
                    mView?.onGetLocationResponse(false, null)
                }
            }
        }

        fun showLocationError(mActivity: Activity) {
            val dialog = AlertDialog.Builder(mActivity)
            dialog.setTitle("Message!")
            dialog.setMessage("Location currently not available\nTry high accuracy mode?")
            dialog.setCancelable(false)
            dialog.setPositiveButton("Yes") { dialogInterface, i ->
                mActivity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                mActivity.finish()
            }.setNegativeButton("Cancel") { dialogInterface, i ->
                dialogInterface.cancel()
            }
            dialog.show()
        }

    }

}