package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_food

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
import com.squadtechs.markhor.foodapp.main_utils.MainUtils

class TraderFragmentFood : Fragment() {

    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TraderFoodAdapter
    private lateinit var list: ArrayList<TraderFoodModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_food, container, false)
        initViews()
        fetchData()
        return mView
    }

    private fun fetchData() {
        val pd = MainUtils.getLoadingDialog(activity!!, "Loading", "Please wait", false)
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/displayalldish.php"
        val requestQueue = Volley.newRequestQueue(activity!!)
        val stringRequest = StringRequest(
            Request.Method.GET,
            API,
            Response.Listener { response ->
                pd.cancel()
                try {
                    val type = object : TypeToken<ArrayList<TraderFoodModel>>() {}.type
                    list = Gson().fromJson(response, type)
                    populateRecyclerView()
                } catch (exc: Exception) {
                    Toast.makeText(activity!!, "There is no dish to display", Toast.LENGTH_LONG)
                        .show()
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Toast.makeText(activity!!, error.toString(), Toast.LENGTH_LONG).show()
            })
        requestQueue.add(stringRequest)
    }

    private fun populateRecyclerView() {
        val position = arguments!!.getInt("key")
        when (position) {
            1 -> {
                var count = (list.size - 1)
                while (count >= 0) {
                    if (!list[count].list_dish_as.equals("Main")) {
                        list.removeAt(count)
                    }
                    count--
                }
            }
            2 -> {
                var count = (list.size - 1)
                while (count >= 0) {
                    if (!list[count].list_dish_as.equals("Side")) {
                        list.removeAt(count)
                    }
                    count--
                }
            }
            3 -> {
                var count = (list.size - 1)
                while (count >= 0) {
                    if (!list[count].list_dish_as.equals("Desert")) {
                        list.removeAt(count)
                    }
                    count--
                }
            }
            4 -> {
                var count = (list.size - 1)
                while (count >= 0) {
                    if (!list[count].list_dish_as.equals("Starters")) {
                        list.removeAt(count)
                    }
                    count--
                }
            }
        }
        adapter = TraderFoodAdapter(list, activity!!)
        inflate()
    }

    private fun inflate() {
        recyclerView.layoutManager = LinearLayoutManager(activity!!)
        recyclerView.adapter = adapter
    }

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.recycler)
        list = ArrayList()
    }

}
