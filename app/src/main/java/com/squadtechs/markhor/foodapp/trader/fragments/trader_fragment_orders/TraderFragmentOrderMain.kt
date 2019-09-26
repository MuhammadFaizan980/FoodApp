package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_orders


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.SingletonQueue
import com.squadtechs.markhor.foodapp.main_utils.MainUtils

class TraderFragmentOrderMain : Fragment() {

    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TraderOrderMainAdapter
    private lateinit var list: ArrayList<TraderOrderMainModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_order_main, container, false)
        initViews()
        fetchData()
        return mView
    }

    private fun fetchData() {
        val API = "http://squadtechsolution.com/android/v1/get_company_order.php"
        val map = HashMap<String, String>()
        map["company_id"] = activity!!.getSharedPreferences(
            "user_credentials",
            Context.MODE_PRIVATE
        ).getString("company_id", "none")!!
        when (arguments!!.getInt("key")) {
            0 -> {
                map["is_complete"] = ""
            }
            1 -> {
                map["is_complete"] = "yes"
            }
        }

        val pd = MainUtils.getLoadingDialog(activity!!, "Loading", "Please wait", false)
        pd.show()
        val requestQueue = SingletonQueue.getRequestQueue(activity!!)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                Log.i("trader_order_response", response)
                try {
                    val type = object : TypeToken<ArrayList<TraderOrderMainModel>>() {}.type
                    list = Gson().fromJson(response, type)
                    recyclerView.layoutManager = LinearLayoutManager(activity!!)
                    adapter = TraderOrderMainAdapter(list, activity!!)
                    recyclerView.adapter = adapter
                } catch (exc: Exception) {
                    Log.i("trader_order_exception", exc.toString())
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Log.i("trader_order_response", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> = map
        }
        requestQueue.add(stringRequest)

    }

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.recycler)
        list = ArrayList()
    }

}
