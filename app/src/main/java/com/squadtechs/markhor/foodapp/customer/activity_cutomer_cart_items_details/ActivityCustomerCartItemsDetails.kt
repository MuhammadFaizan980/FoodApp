package com.squadtechs.markhor.foodapp.customer.activity_cutomer_cart_items_details

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_select_address.ActivityCustomerSelectAddress
import com.squadtechs.markhor.foodapp.customer.db_utiils.CartUtil
import com.squadtechs.markhor.foodapp.customer.db_utiils.DbUtils

class ActivityCustomerCartItemsDetails : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<CartUtil>
    private lateinit var adapter: CartItemsDetailsDetailsAdapter
    private lateinit var db: DbUtils
    private lateinit var companyID: String
    private lateinit var txtTotalPrice: TextView
    private lateinit var txtTotalDeliveryPrice: TextView
    private lateinit var txtDeliverToMe: TextView
    private lateinit var txtIWillCollect: TextView
    private lateinit var txtAddress: TextView
    private lateinit var txtChangeAddress: TextView
    private var address: String = "none"
    private lateinit var pref: SharedPreferences
    private var deliverToMe: String = "yes"
    private var totalPrice: Int = 0
    private var totalDeliveryPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_cart_items_details)
        initViews()
        populateData()
    }

    private fun populateData() {
        list = db.getData(companyID)
        adapter = CartItemsDetailsDetailsAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        calculateTotalPrice()
        calculateDeliveryPrice()
        setListeners()
    }

    private fun setListeners() {
        txtDeliverToMe.setOnClickListener {
            deliverToMe = "yes"
            txtDeliverToMe.setBackgroundResource(R.drawable.tab_back_selected)
            txtIWillCollect.setBackgroundResource(R.drawable.tab_back_unselected)
        }
        txtIWillCollect.setOnClickListener {
            deliverToMe = "no"
            txtDeliverToMe.setBackgroundResource(R.drawable.tab_back_unselected)
            txtIWillCollect.setBackgroundResource(R.drawable.tab_back_selected)
        }

        txtChangeAddress.setOnClickListener {
            startActivityForResult(Intent(this, ActivityCustomerSelectAddress::class.java), 88)
        }

    }

    private fun calculateTotalPrice() {
        for (i in list) {
            totalPrice += (i.cart_item_price.toInt() * i.cart_item_quantity.toInt())
        }
        txtTotalPrice.text = "AED $totalPrice"
    }

    private fun calculateDeliveryPrice() {
        var count = 0;
        for (i in list) {
            if (!i.cart_item_delivery_price.equals("") && i.cart_item_delivery_price != null) {
                totalDeliveryPrice += i.cart_item_delivery_price.toInt()
                count++
            } else {
                continue
            }
        }
        if (count != 0)
            txtTotalDeliveryPrice.text = "AED ${totalDeliveryPrice / count}"
    }

    private fun initViews() {
        txtIWillCollect = findViewById(R.id.i_will_collect)
        txtDeliverToMe = findViewById(R.id.txt_deliver_to_me)
        recyclerView = findViewById(R.id.cart_list)
        list = ArrayList()
        db = DbUtils(this)
        txtTotalPrice = findViewById(R.id.txt_total)
        txtTotalDeliveryPrice = findViewById(R.id.txt_delivery_total)
        companyID = intent!!.extras!!.getString("company_id")!!
        txtAddress = findViewById(R.id.txt_address)
        txtChangeAddress = findViewById(R.id.txt_change_address)
        pref = getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
        address = pref.getString("default_address", "none") as String
        populateAddress()
    }

    @SuppressLint("SetTextI18n")
    private fun populateAddress() {
        if (!address.equals("none")) {
            txtAddress.text = address
        } else {
            txtAddress.text = "No address found, consider adding address from profile section"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 88 && resultCode == Activity.RESULT_OK) {
            address = data!!.extras!!.getString("selected_address")!!
            populateAddress()
        }
    }

}
