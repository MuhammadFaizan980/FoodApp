package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_food


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class CustomerFragmentFood : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomerFoodFragmentAdapter
    private lateinit var list: ArrayList<CustomerFoodFragmentModel>
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.customer_fragment_food, container, false)
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
                Log.i("food_response", response)
                try {
                    val type = object : TypeToken<ArrayList<CustomerFoodFragmentModel>>() {}.type
                    list = Gson().fromJson(response, type)
                    when (arguments!!.getInt("key")) {
                        1 -> {
                            var count = (list.size - 1)
                            while (count >= 0) {
                                if (!list[count].list_dish_as.equals("Starter")) {
                                    list.removeAt(count)
                                }
                                count--
                            }
                        }
                        2 -> {
                            var count = (list.size - 1)
                            while (count >= 0) {
                                if (!list[count].list_dish_as.equals("Main")) {
                                    list.removeAt(count)
                                }
                                count--
                            }
                        }
                        3 -> {
                            var count = (list.size - 1)
                            while (count >= 0) {
                                if (!list[count].list_dish_as.equals("Side")) {
                                    list.removeAt(count)
                                }
                                count--
                            }
                        }
                        4 -> {
                            var count = (list.size - 1)
                            while (count >= 0) {
                                if (!list[count].list_dish_as.equals("Desert")) {
                                    list.removeAt(count)
                                }
                                count--
                            }
                        }
                    }
                    recyclerView.layoutManager = LinearLayoutManager(activity!!)
                    adapter = CustomerFoodFragmentAdapter(
                        list,
                        activity!!,
                        arguments!!.getString("company_id") as String,
                        childFragmentManager
                    )
                    recyclerView.adapter = adapter
                } catch (exc: Exception) {
                    Log.i("food_exception", exc.toString())
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Log.i("food_error", error.toString())
            })
        requestQueue.add(stringRequest)
    }

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.recycler)
        list = ArrayList()
    }

}
