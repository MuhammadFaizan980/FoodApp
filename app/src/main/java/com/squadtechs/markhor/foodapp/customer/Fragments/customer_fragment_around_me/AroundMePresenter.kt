package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_around_me

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.maps.android.SphericalUtil
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.util.CustomerUtils
import org.json.JSONArray

class AroundMePresenter(
    private val mView: AroundMeContracts.IView,
    private val context: Context,
    private val mActivity: Activity
) : AroundMeContracts.IPresenter {

    private lateinit var responseHandlerList: List<AroundMeHttpResponseHandler>
    private lateinit var myLatLng: LatLng

    @SuppressLint("MissingPermission")
    override fun setCurrentLocation(map: GoogleMap) {
        val locationProvider = LocationServices.getFusedLocationProviderClient(context)
        locationProvider.lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                try {
                    val location: Location = task.result!!
                    map.isMyLocationEnabled = true
                    map.uiSettings.isMyLocationButtonEnabled = true
                    myLatLng = LatLng(location.latitude, location.longitude)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 15f))
                    mView.setCurrentLocationResult(true)
                } catch (exc: Exception) {
                    CustomerUtils.showLocationError(mActivity)
                    mView.setCurrentLocationResult(false)
                }
            } else {
                mView.setCurrentLocationResult(false)
                Toast.makeText(
                    context,
                    "There was an error getting your location",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun fetchHttpData() {
        val API = "http://squadtechsolution.com/android/v1/allcompany.php"
        val requestQueue = Volley.newRequestQueue(mActivity)
        val stringRequest = StringRequest(
            Request.Method.GET,
            API,
            Response.Listener { response ->
                if (JSONArray(response).length() != 0) {
                    val typeToken =
                        object : TypeToken<ArrayList<AroundMeHttpResponseHandler>>() {}.type
                    responseHandlerList = Gson().fromJson(response, typeToken)
                    mView.onFetchHttpDataResult(true)
                } else {
                    mView.onFetchHttpDataResult(false)
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                Log.i("dxdiag", error.printStackTrace().toString())
                mView.onFetchHttpDataResult(false)
            })
        stringRequest.retryPolicy = DefaultRetryPolicy(
            10000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        requestQueue.add(stringRequest)
    }

    override fun setMarkers(map: GoogleMap, distance: Int) {
        map.clear()
        for (i in responseHandlerList) {
            if (!i.delivery_type.equals("")) {
                val latLng = CustomerUtils.decodeCoordinates(i.address)
                val calculatedDistance = SphericalUtil.computeDistanceBetween(myLatLng, latLng)
                if (calculatedDistance <= distance) {
                    val marker = MarkerOptions() as MarkerOptions
                    marker.title(i.company_name)
                    marker.snippet(i.company_description)
                    marker.position(latLng)
                    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.dark_marker))
                    map.addMarker(marker)
                }
            } else {
                continue
            }
        }
    }
}