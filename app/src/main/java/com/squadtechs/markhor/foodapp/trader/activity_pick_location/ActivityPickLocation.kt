package com.squadtechs.markhor.foodapp.trader.activity_pick_location

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.squadtechs.markhor.foodapp.R

class ActivityPickLocation : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_location)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    private fun addDraggableMarker() {
        val marker = MarkerOptions()
        marker.draggable(true)
        mMap.clear()
        mMap.addMarker(marker)
        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragEnd(p0: Marker?) {
                Toast.makeText(
                    this@ActivityPickLocation,
                    p0!!.position.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onMarkerDragStart(p0: Marker?) {}

            override fun onMarkerDrag(p0: Marker?) {}
        })
    }

}
