package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_non_food


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

class FragmentCustomerNonFood : Fragment() {
    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomerFragmentNonFoodAdapter
    private lateinit var list: ArrayList<CustomerFragmentNonFoodModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_customer_clothes, container, false)
        initViews()
        fetchData()
        return mView
    }

    private fun fetchData() {
        val pd = MainUtils.getLoadingDialog(activity!!, "Loading", "Please wait", false)
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/displayAllNonFood.php"
        val requestQueue = Volley.newRequestQueue(activity!!)
        val stringRequest = StringRequest(
            Request.Method.GET,
            API,
            Response.Listener { response ->
                pd.cancel()
                Log.i("food_response", response)
                try {
                    val type = object : TypeToken<ArrayList<CustomerFragmentNonFoodModel>>() {}.type
                    list = Gson().fromJson(response, type)
                    when (arguments!!.getInt("position")) {
                        1 -> {
                            var count = (list.size - 1)
                            while (count >= 0) {
                                if (!list[count].list_item_as.equals("Women Fashion")) {
                                    list.removeAt(count)
                                }
                                count--
                            }
                        }
                        2 -> {
                            var count = (list.size - 1)
                            while (count >= 0) {
                                if (!list[count].list_item_as.equals("Men Fashion")) {
                                    list.removeAt(count)
                                }
                                count--
                            }
                        }
                        3 -> {
                            var count = (list.size - 1)
                            while (count >= 0) {
                                if (!list[count].list_item_as.equals("Children")) {
                                    list.removeAt(count)
                                }
                                count--
                            }
                        }
                    }
                    recyclerView.layoutManager = GridLayoutManager(activity!!, 2)
                    adapter = CustomerFragmentNonFoodAdapter(list, activity!!)
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
