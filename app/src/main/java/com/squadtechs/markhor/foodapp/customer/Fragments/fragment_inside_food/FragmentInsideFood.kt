package com.squadtechs.markhor.foodapp.customer.Fragments.fragment_inside_food

import android.app.ProgressDialog
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squadtechs.markhor.foodapp.R

class FragmentInsideFood : Fragment() {

    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView

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
                //TODO: load nearby only
            }
            1 -> {
                makeRequest()
            }
            2 -> {
                //TODO: load deliver only
            }
        }
    }

    private fun makeRequest() {
        val pd = ProgressDialog(activity!!)
        pd.setCancelable(false)
        pd.setTitle("Loading")
        pd.setMessage("Please wait")
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/allcompany.php"
        val requestQueue = Volley.newRequestQueue(activity!!.applicationContext)
        val stringRequest = StringRequest(
            Request.Method.GET,
            API,
            Response.Listener { response ->
                pd.cancel()
                val type = object : TypeToken<ArrayList<InsIdeFoodModel>>() {}.type
                val list: ArrayList<InsIdeFoodModel> = Gson().fromJson(response, type)
                var count = 0
//                for (i in list) {
//                    if (!i.company_type.equals("Food"))
//                        list.remove(i)
//                }

                recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                val adapter = InsideFoodAdapter(
                    list,
                    activity!!.applicationContext,
                    arguments!!.getInt("key")
                )
                recyclerView.adapter = adapter

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

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.recycler)
    }

}
