package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_home_non_food

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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

class TraderFragmentHomeNonFood : Fragment() {

    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TraderNonFoodAdapter
    private lateinit var list: ArrayList<TraderNonFoodModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_home_non_food, container, false)
        initViews()
        getData()
        return mView
    }

    private fun getData() {
        val pd = MainUtils.getLoadingDialog(activity!!, "Loading", "Please wait", false)
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/displayAllNonFood.php"
        val requestQueue = Volley.newRequestQueue(activity!!)
        val stringRequest = StringRequest(
            Request.Method.GET,
            API,
            Response.Listener { response ->
                pd.cancel()
                Log.i("non_food_response", response)
                try {
                    val type = object : TypeToken<ArrayList<TraderNonFoodModel>>() {}.type
                    list = Gson().fromJson(response, type)

                    val trader_id =
                        activity!!.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                            .getString("id", "none")
                    var count = (list.size - 1)
                    while (count >= 0) {
                        if (!list[count].trader_id.equals(trader_id)) {
                            list.removeAt(count)
                        }
                        count--
                    }
                    recyclerView.layoutManager = GridLayoutManager(activity!!, 2)
                    adapter = TraderNonFoodAdapter(list, activity!!)
                    recyclerView.adapter = adapter

                } catch (exc: Exception) {
                    Log.i("non_food_exception", exc.toString())
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Log.i("non_food_response", error.toString())
            })
        requestQueue.add(stringRequest)
    }

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.recycler)
        list = ArrayList()
    }

}
