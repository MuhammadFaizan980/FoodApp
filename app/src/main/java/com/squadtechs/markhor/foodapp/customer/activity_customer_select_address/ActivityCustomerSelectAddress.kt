package com.squadtechs.markhor.foodapp.customer.activity_customer_select_address

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
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
import com.squadtechs.markhor.foodapp.main_utils.MainUtils

class ActivityCustomerSelectAddress : AppCompatActivity(), SelectContract {

    private lateinit var imgGoBack: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SelectAdapter
    private lateinit var list: ArrayList<SelectModel>
    private lateinit var customerID: String
    private val API = "http://squadtechsolution.com//android/v1/get_customer_address.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_address)
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
                    val type = object : TypeToken<ArrayList<SelectModel>>() {}.type
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
        adapter = SelectAdapter(list, this, this@ActivityCustomerSelectAddress)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setListeners() {
        imgGoBack.setOnClickListener {
            finish()
        }
    }

    private fun initViews() {
        imgGoBack = findViewById(R.id.img_go_back)
        recyclerView = findViewById(R.id.recycler)
    }

    override fun addressSelected(address: String) {
        val intent = Intent()
        intent.putExtra("selected_address", address)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onBackPressed() {
        finish()
    }

}
