package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_non_food


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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

                    var count = (list.size - 1)
                    while (count >= 0) {
                        if (!list[count].company_id.equals(arguments!!.getString("company_id"))) {
                            list.removeAt(count)
                        }
                        count--
                    }
                    populateList()
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

    private fun populateList() {

        when (arguments!!.get("company_type") as String) {
            "Clothes" -> {
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
            }
            "Accessories" -> {
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
                    4 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Unisex")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    5 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Other")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                }
            }
            "Skincare" -> {
                when (arguments!!.getInt("position")) {
                    1 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Face & Body")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    2 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Nails")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    3 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Other")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                }
            }
            "Homeware" -> {
                when (arguments!!.getInt("position")) {
                    1 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Bed Room")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    2 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Living Room")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    3 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Patio")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    4 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Other")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                }
            }
            "Toys" -> {
                when (arguments!!.getInt("position")) {
                    1 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Boys")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    2 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Girls")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    3 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Unisex")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    4 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Adults")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    5 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Other")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                }
            }
            "Shoes" -> {
                when (arguments!!.getInt("position")) {
                    1 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Men")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    2 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Women")) {
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
                    4 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Unisex")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                }
            }
            "Bags" -> {
                when (arguments!!.getInt("position")) {
                    1 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Hand Bags")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                    2 -> {
                        var count = (list.size - 1)
                        while (count >= 0) {
                            if (!list[count].list_item_as.equals("Other")) {
                                list.removeAt(count)
                            }
                            count--
                        }
                    }
                }
            }
        }

        recyclerView.layoutManager = GridLayoutManager(activity!!, 2)
        adapter = CustomerFragmentNonFoodAdapter(
            list,
            activity!!,
            arguments!!.getString("company_id")!!
        )
        recyclerView.adapter = adapter
    }

    private fun initViews() {
        recyclerView = mView.findViewById(R.id.recycler)
        list = ArrayList()
    }

}
