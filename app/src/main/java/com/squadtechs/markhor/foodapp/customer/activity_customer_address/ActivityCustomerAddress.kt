package com.squadtechs.markhor.foodapp.customer.activity_customer_address

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_add_address.ActivityCustomerAddAddress
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.ActivityCustomerMain
import com.squadtechs.markhor.foodapp.main_utils.MainUtils

class ActivityCustomerAddress : AppCompatActivity() {

    private lateinit var txtAddNewAddress: TextView
    private lateinit var imgGoBack: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomerAddressAdapter
    private lateinit var list: ArrayList<CustomerAddressModel>
    private lateinit var customerID: String
    private val API = "http://squadtechsolution.com//android/v1/get_customer_address.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_address)
        initViews()
        setListeners()
        fetchHttpData()
    }

    private fun fetchHttpData() {
        val pd = MainUtils.getLoadingDialog(this, "Loading", "Please wait", false)
        pd.show()
        customerID = getSharedPreferences("user_credentials", Context.MODE_PRIVATE).getString(
            "id",
            "n/a"
        ) as String
        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(
            Method.POST,
            API,
            Response.Listener { response ->
                pd.cancel()
                Log.i("dxdiag", response)
                try {
                    list = ArrayList()
                    val type = object : TypeToken<ArrayList<CustomerAddressModel>>() {}.type
                    list = Gson().fromJson(response, type)
                    populateRecyclerView()
                } catch (exc: Exception) {
                    Toast.makeText(
                        this,
                        "No address found\nConsider adding new address first",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            Response.ErrorListener { error ->
                pd.cancel()
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
                Log.i("dxdiag", error.toString())
            }) {
            override fun getParams(): MutableMap<String, String> {
                val map = HashMap<String, String>()
                map["id"] = customerID
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

    private fun populateRecyclerView() {
        adapter = CustomerAddressAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setListeners() {
        txtAddNewAddress.setOnClickListener {
            startActivity(Intent(this, ActivityCustomerAddAddress::class.java))
            finish()
        }

        imgGoBack.setOnClickListener {
            startActivity(Intent(this, ActivityCustomerMain::class.java))
            finish()
        }
    }

    private fun initViews() {
        txtAddNewAddress = findViewById(R.id.txt_customer_address)
        imgGoBack = findViewById(R.id.img_go_back)
        recyclerView = findViewById(R.id.recycler)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityCustomerMain::class.java))
        finish()
    }

}
