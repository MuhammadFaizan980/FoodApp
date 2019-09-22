package com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_companies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.ActivityCustomerMain
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import kotlinx.android.synthetic.main.recycler_view.*

class ActivityCustomerNonFoodCompanies : AppCompatActivity() {

    private lateinit var txtTitle: TextView
    private lateinit var txtSubTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomerNonFoodCompaniesAdapter
    private lateinit var list: ArrayList<CustomerNonFoodCompaniesModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_non_food_companies)
        initViews()
        fetchData()
    }

    private fun fetchData() {
        val pd = MainUtils.getLoadingDialog(this, "Loading", "Please wait", false)
        pd.show()
        val API = "http://squadtechsolution.com/android/v1/allcompany.php"
        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            API,
            Response.Listener { response ->
                Log.i("non_food_companies", response)
                pd.cancel()
                try {
                    val type =
                        object : TypeToken<ArrayList<CustomerNonFoodCompaniesModel>>() {}.type
                    list = Gson().fromJson(response, type)
                    when (intent!!.extras!!.getInt("position")) {
                        0 -> {
                            var count =  (list.size - 1 )
                            while (count >= 0) {
                                if (!list[count].company_type.equals("Clothes")) {
                                    list.removeAt(count)
                                }
                                count--
                            }
                        }
                    }
                    adapter = CustomerNonFoodCompaniesAdapter(list, this)
                    recycler.layoutManager = GridLayoutManager(this, 2)
                    recyclerView.adapter = adapter

                } catch (exc: Exception) {
                    Toast.makeText(this, "No company found", Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
                Log.i("non_food_companies", error.toString())
                pd.cancel()
            })
        requestQueue.add(stringRequest)
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        txtTitle = findViewById(R.id.txt_title)
        txtSubTitle = findViewById(R.id.txt_subtitle)
        recyclerView = findViewById(R.id.recycler)
        list = ArrayList()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityCustomerMain::class.java))
        finish()
    }

}
