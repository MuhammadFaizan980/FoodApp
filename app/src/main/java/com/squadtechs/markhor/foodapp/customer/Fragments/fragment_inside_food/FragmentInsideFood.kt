package com.squadtechs.markhor.foodapp.customer.Fragments.fragment_inside_food

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.maps.android.SphericalUtil
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.customer_util.CustomerUtils
import com.squadtechs.markhor.foodapp.main_utils.MainUtils

class FragmentInsideFood : Fragment(), InsideFoodContracts {

    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var deliverOnlyList: ArrayList<InsIdeFoodModel>
    private lateinit var nearMeList: ArrayList<InsIdeFoodModel>
    private lateinit var list: ArrayList<InsIdeFoodModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_inside_food, container, false)
        initViews()
        populateRecyclerView()
        return mView
    }

    private fun populateRecyclerView() {
        val position = arguments!!.getInt("key")
        when (position) {
            0 -> {
                makeRequest(0)
            }
            1 -> {
                makeRequest(1)
            }
            2 -> {
                makeRequest(2)
            }
        }
    }

    private fun makeRequest(tabPosition: Int) {
        val pd = MainUtils.getLoadingDialog(
            activity!!,
            "Loading",
            "Please wait",
            false
        )
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/allcompany.php"
        val requestQueue = Volley.newRequestQueue(activity!!.applicationContext)
        val stringRequest = StringRequest(
            Request.Method.GET,
            API,
            Response.Listener { response ->
                pd.cancel()
                val type = object : TypeToken<ArrayList<InsIdeFoodModel>>() {}.type
                list = Gson().fromJson(response, type)

                var count = (list.size - 1)
                while (count >= 0) {
                    if (!list[count].company_type.equals("Food & beverages")) {
                        list.removeAt(count)
                    }
                    count--
                }

                if (tabPosition == 2) {
                    deliverOnlyList = ArrayList<InsIdeFoodModel>()
                    for (i in list) {
                        if (i.delivery_type.equals("yes")) {
                            deliverOnlyList.add(i)
                        }
                    }
                    populateListAccordingly(tabPosition)
                } else if (tabPosition == 0) {
                    CustomerUtils.getCurrentLocation(
                        this,
                        LocationServices.getFusedLocationProviderClient(activity!!),
                        activity!!
                    )
                } else {
                    populateListAccordingly(1)
                }

            },
            Response.ErrorListener { error ->
                pd.cancel()
                Toast.makeText(
                    activity!!.applicationContext,
                    "There was an error",
                    Toast.LENGTH_LONG
                ).show()
            })
        requestQueue.add(stringRequest)
    }

    private fun populateListAccordingly(tabPosition: Int) {
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        when (tabPosition) {
            1 -> {
                val adapter = InsideFoodAdapter(
                    list,
                    activity!!.applicationContext,
                    tabPosition
                )
                recyclerView.adapter = adapter
            }
            2 -> {
                val adapter = InsideFoodAdapter(
                    deliverOnlyList,
                    activity!!.applicationContext,
                    tabPosition
                )
                recyclerView.adapter = adapter
            }
        }
    }

    override fun onGetLocationResponse(status: Boolean, location: Location?) {
        if (status) {
            val mLatLng = LatLng(location!!.latitude, location!!.longitude)
            nearMeList = ArrayList()
            for (i in list) {
                val companyLatLng = CustomerUtils.decodeCoordinates(i.address)
                if (SphericalUtil.computeDistanceBetween(
                        mLatLng,
                        companyLatLng
                    ) <= 5000
                ) {
                    nearMeList.add(i)
                }
            }
            recyclerView.layoutManager = LinearLayoutManager(activity!!)
            val adapter = InsideFoodAdapter(
                nearMeList,
                activity!!.applicationContext,
                0
            )
            recyclerView.adapter = adapter
        } else {
            CustomerUtils.showLocationError(activity!!)
        }
    }

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.recycler)
    }

}
