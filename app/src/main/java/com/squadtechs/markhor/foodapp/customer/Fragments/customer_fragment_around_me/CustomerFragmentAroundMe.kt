package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.squadtechs.markhor.foodapp.R

class CustomerFragmentAroundMe : Fragment(), OnMapReadyCallback, AroundMeContracts.IView {

    private lateinit var mapView: SupportMapFragment
    private lateinit var map: GoogleMap
    private lateinit var mPresenter: AroundMeContracts.IPresenter
    private var permissionCheck: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.customer_fragment_around_me, container, false)
        initViews(view)
        mPresenter.checkPermissions()
        return view
    }

    private fun initViews(view: View) {
        mapView = childFragmentManager.findFragmentById(R.id.map_around_me) as SupportMapFragment
        mapView.getMapAsync(this)
        mPresenter = AroundMePresenter(this, activity!!.applicationContext, activity!!)
    }

    override fun onPerssionsResult(status: Boolean) {
        permissionCheck = status
        if (status) {
            Toast.makeText(activity!!.applicationContext, "Permission granted", Toast.LENGTH_LONG)
                .show()
        } else {
            Toast.makeText(activity!!.applicationContext, "Permission denied", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        map = p0!!
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 99 && grantResults.isNotEmpty()) {
            for (i in grantResults) {
                if (i == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(
                        activity!!.applicationContext,
                        "Permissions are required for app to work properly",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
            }
        }
    }

}
