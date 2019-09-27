package com.squadtechs.markhor.foodapp.customer.activity_customer_order_details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.SingletonQueue
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONObject

class ActivityCustomerOrderDetails : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomerOrderDetailsAdapter
    private lateinit var list: ArrayList<CustomerOrderDetailsModel>
    private lateinit var txtPrice: TextView
    private lateinit var txtDeliveryPrice: TextView
    private lateinit var txtAllergyRequest: TextView
    private lateinit var imgProgress: ImageView
    private lateinit var btnMarkAsComplete: Button
    private var totalPrice: Int = 0
    private var totalDeliveryPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_order_details)
        initViews()
        populateRecyclerView()
        setListener()
    }

    private fun setListener() {
        btnMarkAsComplete.setOnClickListener {
            val ad = MainUtils.getMessageDialog(
                this,
                "Info",
                "Are you sure you want to mark this order as complete?",
                false
            )
            ad.setPositiveButton("Mark as complete") { dialogInterface, i ->
                dialogInterface.dismiss()
                val pd = MainUtils.getLoadingDialog(this, "Loading", "Please wait", false)
                pd.show()
                val API = "http://squadtechsolution.com/android/v1/update_order_isComlete.php"
                val requestQueue = SingletonQueue.getRequestQueue(this)
                val map = HashMap<String, String>()
                map["customer_id"] = list[0].customer_id
                map["company_id"] = list[0].company_id
                map["is_complete"] = "yes"
                val stringRequest = object : StringRequest(
                    Method.POST,
                    API,
                    Response.Listener { response ->
                        pd.dismiss()
                        Log.i("m_faizan", response)
                        try {
                            if (JSONObject(response).getString("status").equals("Order Status Updated")) {
                                //TODO: handle success
                            } else {
                                //TODO: handle failure
                            }
                        } catch (exc: Exception) {
                            Log.i("m_exception", exc.toString())
                        }
                    },
                    Response.ErrorListener { error ->
                        pd.dismiss()
                        Log.i("m_faizan", error.toString())
                    }) {
                    override fun getParams(): MutableMap<String, String> = map
                }
                requestQueue.add(stringRequest)
            }
                .setNegativeButton("Cancel") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
            ad.show()
        }
    }

    private fun populateRecyclerView() {
        val map = HashMap<String, String>()
        map["customer_id"] =
            getSharedPreferences("user_credentials", Context.MODE_PRIVATE).getString("id", "none")!!
        map["company_id"] = intent!!.extras!!.getString("company_id")!!
        val API = "http://squadtechsolution.com/android/v1/get _all_order_for_customer.php"
        when (intent!!.extras!!.getInt("position")) {
            0 -> {
                map["is_complete"] = ""
            }
            1 -> {
                map["is_complete"] = "yes"
            }
        }
        val pd = MainUtils.getLoadingDialog(this, "Loading", "Please wait", false)
        pd.cancel()
        val requestQueue = SingletonQueue.getRequestQueue(this)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                Log.i("customer_order_response", response)
                try {
                    val type = object : TypeToken<ArrayList<CustomerOrderDetailsModel>>() {}.type
                    list = Gson().fromJson(response, type)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    adapter = CustomerOrderDetailsAdapter(list, this)
                    txtAllergyRequest.text = "Allergy request: ${list[0].request}"
                    recyclerView.adapter = adapter
                    calculateTotalPrice()
                } catch (exc: Exception) {
                    Log.i("customer_exception", exc.toString())
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Log.i("customer_order_response", error.toString())
            }) {

            override fun getParams(): MutableMap<String, String> = map
        }
        requestQueue.add(stringRequest)
    }

    private fun calculateTotalPrice() {
        for (i in list) {
            totalPrice += (i.itemPrice.toInt() * i.itemPrice.toInt())
        }
        txtPrice.text = "AED $totalPrice"
        calculateDeliveryPrice()
    }

    private fun calculateDeliveryPrice() {
        var count = 0
        for (i in list) {
            if (!i.deliveryPrice.equals("") && i.deliveryPrice != null) {
                totalDeliveryPrice += i.deliveryPrice.toInt()
                count++
            } else {
                continue
            }
        }
        if (count != 0)
            txtDeliveryPrice.text = "AED ${totalDeliveryPrice / count}"
        setOrderProgress()
    }

    private fun setOrderProgress() {
        when (list[0].status) {
            "pending" -> {
                imgProgress.setImageResource(R.drawable.status_waiting)
            }
            "being prepared" -> {
                imgProgress.setImageResource(R.drawable.status_preparing)
            }
            "on the way" -> {
                imgProgress.setImageResource(R.drawable.status_on_the_way)
            }
            "ready" -> {
                btnMarkAsComplete.visibility = View.VISIBLE
                imgProgress.setImageResource(R.drawable.status_ready_for_pick_up)
            }
        }
        setVisibilities()
    }

    private fun setVisibilities() {
        when (intent!!.extras!!.getInt("position")) {
            1 -> {
                imgProgress.visibility = View.GONE
                txtAllergyRequest.visibility = View.GONE
                btnMarkAsComplete.visibility = View.GONE
            }
        }
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.order_list)
        txtPrice = findViewById(R.id.txt_total)
        txtDeliveryPrice = findViewById(R.id.txt_delivery_total)
        txtAllergyRequest = findViewById(R.id.txt_allergy)
        btnMarkAsComplete = findViewById(R.id.btn_complete)
        imgProgress = findViewById(R.id.img_progress)
    }

    override fun onStart() {
        super.onStart()
        btnMarkAsComplete.visibility = View.GONE
    }

}
