package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_food

import android.app.Activity
import android.content.Context
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
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.TraderMainCallBack

class TraderFragmentFood : Fragment(), FragmentFoodCallback {

    private lateinit var mView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TraderFoodAdapter
    private lateinit var list: ArrayList<TraderFoodModel>
    private lateinit var obj: TraderMainCallBack

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
                    val id =
                        activity!!.getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                            .getString("id", "none") as String

                    var count = (list.size - 1)
                    while (count >= 0) {
                        if (!list[count].trader_id.equals(id)) {
                            list.removeAt(count)
                        }
                        count--
                    }
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
        adapter = TraderFoodAdapter(list, activity!!, this)
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

    override fun onEditClicked() {
        obj.onEditClicked()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        obj = activity!! as TraderMainCallBack
    }

}
