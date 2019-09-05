package com.squadtechs.markhor.foodapp.trader.activity_pick_location

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.squadtechs.markhor.foodapp.R

class ActivityPickLocation : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var btnPickLocation: TextView
    private lateinit var marker: MarkerOptions

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
        addDraggableMarker()
    }

    private fun addDraggableMarker() {
        shoeInfoDialog()
        marker = MarkerOptions()
        marker.position(LatLng(23.656444, 54.006528))
        marker.draggable(true)
        mMap.clear()
        mMap.addMarker(marker)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(23.656444, 54.006528), 7f))
        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragEnd(p0: Marker?) {
                btnPickLocation.visibility = View.VISIBLE
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
                "${marker.position.latitude}, ${marker.position.longitude}"
            )
            editor.apply()
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }.setNegativeButton("Cancel") { dialogInterface, i ->
            dialogInterface.cancel()
        }
        dialog.show()
    }

}
