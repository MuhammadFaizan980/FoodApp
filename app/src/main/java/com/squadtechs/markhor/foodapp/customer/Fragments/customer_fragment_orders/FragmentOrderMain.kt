package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_orders

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class FragmentOrderMain : Fragment() {

    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<OrderMainModel>
    private lateinit var adapter: OrderMainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_fragment_order_main, container, false)
        initViews()
        populateRecyclerView(arguments!!.getInt("key"))
        return mView
    }

    private fun populateRecyclerView(key: Int) {
        val pd = MainUtils.getLoadingDialog(activity!!, "Loading", "Please wait", false)
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/get_customer_order.php"
        val map = HashMap<String, String>()
        map["customer_id"] = activity!!.getSharedPreferences(
            "user_credentials",
            Context.MODE_PRIVATE
        ).getString("id", "none")!!
        when (key) {
            0 -> {
                map["is_complete"] = ""
            }
            1 -> {
                map["is_complete"] = "yes"
            }
        }
        val requestQueue = SingletonQueue.getRequestQueue(activity!!)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                try {
                    val type = object : TypeToken<ArrayList<OrderMainModel>>() {}.type
                    list = Gson().fromJson(response, type)
                    adapter = OrderMainAdapter(list, activity!!)
                    recyclerView.layoutManager = LinearLayoutManager(activity!!)
                    recyclerView.adapter = adapter
                } catch (exc: Exception) {
                    Log.i("order_exception", exc.toString())
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Toast.makeText(activity!!, error.toString(), Toast.LENGTH_LONG).show()
                Log.i("order_error", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> = map
        }
        requestQueue.add(stringRequest)
    }

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.main_list)
        list = ArrayList()
    }

}
