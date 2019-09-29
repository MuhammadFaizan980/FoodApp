package com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_companies_details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.CONSTANTS
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_to_trader_chat.ActivityCustomerToTraderChat
import com.squareup.picasso.Picasso
import org.json.JSONArray

class ActivityCustomerNonFoodCompaniesDetails : AppCompatActivity() {
    private lateinit var viewPackage: ViewPager
    private lateinit var txtAll: TextView
    private lateinit var txtWomen: TextView
    private lateinit var txtMen: TextView
    private lateinit var txtChildren: TextView

    private lateinit var txtTitle: TextView
    private lateinit var txtTime: TextView
    private lateinit var txtDeliveryType: TextView
    private lateinit var txtDescription: TextView
    private lateinit var imgCompany: ImageView
    private lateinit var imgChat: ImageView
    private lateinit var imgBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_non_food_companies_details)
        initViews()
        setListeners()
    }

    private fun setListeners() {

        txtAll.setOnClickListener {
            txtAll.setBackgroundResource(R.drawable.tab_back_selected)
            txtWomen.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMen.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildren.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(0)
        }
        txtWomen.setOnClickListener {
            txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomen.setBackgroundResource(R.drawable.tab_back_selected)
            txtMen.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildren.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(1)
        }
        txtMen.setOnClickListener {
            txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomen.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMen.setBackgroundResource(R.drawable.tab_back_selected)
            txtChildren.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(2)
        }
        txtChildren.setOnClickListener {
            txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomen.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMen.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildren.setBackgroundResource(R.drawable.tab_back_selected)
            viewPackage.setCurrentItem(3)
        }

        viewPackage.adapter = ClothesPagerAdapter(
            intent!!.extras!!.get("company_id") as String,
            supportFragmentManager
        )
        viewPackage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_selected)
                        txtWomen.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMen.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtChildren.setBackgroundResource(R.drawable.tab_back_unselected)
                    }

                    1 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtWomen.setBackgroundResource(R.drawable.tab_back_selected)
                        txtMen.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtChildren.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    2 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtWomen.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMen.setBackgroundResource(R.drawable.tab_back_selected)
                        txtChildren.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    3 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtWomen.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMen.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtChildren.setBackgroundResource(R.drawable.tab_back_selected)
                    }
                }
            }

        })

        imgBack.setOnClickListener {
            finish()
        }

        imgChat.setOnClickListener {
            takeUserToChatScreen()
        }


    }

    private fun initViews() {
        imgChat = findViewById(R.id.img_chat)
        viewPackage = findViewById(R.id.clothes_view_pager)
        txtAll = findViewById(R.id.txt_all)
        txtWomen = findViewById(R.id.txt_women)
        txtMen = findViewById(R.id.txt_men)
        txtChildren = findViewById(R.id.txt_children)

        txtTitle = findViewById(R.id.txt_title)
        txtDescription = findViewById(R.id.txt_description)
        txtTime = findViewById(R.id.txt_time)
        txtDeliveryType = findViewById(R.id.txt_delivery_type)
        imgCompany = findViewById(R.id.img_company_image)
        imgBack = findViewById(R.id.img_go_back)
        fetchSingleCompanyData()
    }

    private fun takeUserToChatScreen() {
        val mIntent = Intent(this, ActivityCustomerToTraderChat::class.java)
        mIntent.putExtra("company_id", intent!!.extras!!.get("company_id") as String)
        startActivity(mIntent)
    }

    private fun fetchSingleCompanyData() {
        val API = "http://squadtechsolution.com/android/v1/allcompany.php"
        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, API,
            Response.Listener { response ->
                Log.i("dxdiag", response)
                try {

                    val jsonArr = JSONArray(response)
                    var count = (jsonArr.length() - 1)
                    while (count >= 0) {
                        val json = jsonArr.getJSONObject(count)
                        if (json.getString("id").equals(intent!!.extras!!.get("company_id") as String)
                        ) {
                            Picasso.get()
                                .load("${CONSTANTS.imgPre}${json.getString("company_logo")}")
                                .into(imgCompany)
                            txtTitle.text = json.getString("company_name")
                            txtDeliveryType.text = "Delivery: ${json.getString("delivery_type")}"
                            txtTime.text = json.getString("delivery_timing")
                            txtDescription.text = json.getString("company_description")
                            break
                        }
                        count--
                    }

                } catch (exc: Exception) {
                    Log.i("dxdiag", exc.toString())
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            })
        requestQueue.add(stringRequest)
    }

}
