package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MapStyleOptions
import com.squadtechs.markhor.foodapp.R
import com.xw.repo.BubbleSeekBar


class CustomerFragmentAroundMe : Fragment(), OnMapReadyCallback, AroundMeContracts.IView {

    private lateinit var mapView: SupportMapFragment
    private lateinit var map: GoogleMap
    private lateinit var mPresenter: AroundMeContracts.IPresenter
    private lateinit var distanceSeeker: BubbleSeekBar
    private var distance: Int = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.customer_fragment_around_me, container, false)
        initViews(view)
        setSeekListener(view)
        return view
    }

    private fun setSeekListener(view: View) {
        distanceSeeker.onProgressChangedListener =
            object : BubbleSeekBar.OnProgressChangedListener {
                override fun onProgressChanged(
                    bubbleSeekBar: BubbleSeekBar?,
                    progress: Int,
                    progressFloat: Float,
                    fromUser: Boolean
                ) {
                }

                override fun getProgressOnActionUp(
                    bubbleSeekBar: BubbleSeekBar?,
                    progress: Int,
                    progressFloat: Float
                ) {
                    mPresenter.setMarkers(map, progress)
                }

                override fun getProgressOnFinally(
                    bubbleSeekBar: BubbleSeekBar?,
                    progress: Int,
                    progressFloat: Float,
                    fromUser: Boolean
                ) {
                }
            }
    }

    private fun initViews(view: View) {
        mapView = childFragmentManager.findFragmentById(R.id.map_around_me) as SupportMapFragment
        mapView.getMapAsync(this)
        mPresenter = AroundMePresenter(this, activity!!.applicationContext, activity!!)
        distanceSeeker = view.findViewById(R.id.distance_seeker)
    }

    override fun setCurrentLocationResult(status: Boolean) {
        if (status) {
            mPresenter.fetchHttpData()
        }
    }

    override fun onFetchHttpDataResult(status: Boolean) {
        if (status) {
            mPresenter.setMarkers(map, distance)
        } else {
            Toast.makeText(
                activity!!.applicationContext,
                "There was an error fetching data",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        map = p0!!
        mPresenter.setCurrentLocation(map)
        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    activity!!.applicationContext, com.squadtechs.markhor.foodapp.R.raw.maps_style
                )
            )
            if (!success) {
                Log.e("MapsActivityRaw", "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("MapsActivityRaw", "Can't find style.", e)
        }
    }
}
