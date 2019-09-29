package com.squadtechs.markhor.foodapp.trader.activity_pick_location

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.main_utils.MainUtils

class ActivityPickLocation : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var btnPickLocation: TextView
    private lateinit var marker: MarkerOptions
    private lateinit var latLng: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_location)
        btnPickLocation = findViewById(R.id.btn_pick_this_location)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        checkPermissions()
    }

    private fun addDraggableMarker(lat: Double, lng: Double) {
        shoeInfoDialog()
        marker = MarkerOptions()
        marker.position(LatLng(lat, lng))
        marker.draggable(true)
        mMap.clear()
        mMap.addMarker(marker)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 7f))
        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragEnd(p0: Marker?) {
                btnPickLocation.visibility = View.VISIBLE
                latLng = LatLng(p0!!.position.latitude, p0.position.longitude)
            }

            override fun onMarkerDragStart(p0: Marker?) {
                btnPickLocation.visibility = View.GONE
            }

            override fun onMarkerDrag(p0: Marker?) {}
        })
    }

    private fun shoeInfoDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Info!")
        dialog.setMessage("Tap and hold the marker and drop it on your desired location")
        dialog.setCancelable(false)
        dialog.setPositiveButton("Close") { dialogInterface, i ->
            dialogInterface.cancel()
        }
        dialog.show()
    }

    fun pickLocation(view: View) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Message")
        dialog.setMessage("Are you sure you want to pick this location?")
        dialog.setCancelable(false)
        dialog.setPositiveButton("Confirm") { dialogInterface, i ->
            val returnIntent = Intent()
            val pref = getSharedPreferences("coordinates", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(
                "coordinates",
                "${latLng.latitude}, ${latLng.longitude}"
            )
            editor.apply()
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }.setNegativeButton("Cancel") { dialogInterface, i ->
            dialogInterface.cancel()
        }
        dialog.show()
    }

    fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            val permissionArray = arrayOfNulls<String>(1)
            permissionArray[0] = android.Manifest.permission.ACCESS_FINE_LOCATION
            ActivityCompat.requestPermissions(this@ActivityPickLocation, permissionArray, 99)
        } else {
            getCurrentLocation()
        }
    }

    fun getCurrentLocation(
    ) {
        var location: Location?
        val locationProvider = LocationServices.getFusedLocationProviderClient(this)
        locationProvider.lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                location = task.result
                if (location != null) {
                    addDraggableMarker(location!!.latitude, location!!.longitude)
                } else {
                    val ad = MainUtils.getMessageDialog(
                        this,
                        "Message!",
                        "location currently not available",
                        false
                    )
                    ad.setPositiveButton("Close") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                    ad.show()
                }
            } else {
                val ad = MainUtils.getMessageDialog(
                    this,
                    "Message!",
                    "location currently not available",
                    false
                )
                ad.setPositiveButton("Close") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                ad.show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 99 && grantResults.isNotEmpty()) {
            for (i in grantResults) {
                Log.i("dxdiag", i.toString())
                if (i == PackageManager.PERMISSION_GRANTED) {
                    continue
                } else {
                    Toast.makeText(
                        this,
                        "Permissions are required for the app to work properly",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
            }
            getCurrentLocation()
        }
    }

}
