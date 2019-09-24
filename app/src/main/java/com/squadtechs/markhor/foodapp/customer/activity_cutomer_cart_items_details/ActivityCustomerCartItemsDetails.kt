package com.squadtechs.markhor.foodapp.customer.activity_cutomer_cart_items_details

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
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
    }

    private fun calculateTotalPrice() {
        for (i in list) {
            totalPrice += (i.cart_item_price.toInt()*i.cart_item_quantity.toInt())
        }
        txtTotalPrice.text = "AED $totalPrice"
    }

    private fun calculateDeliveryPrice() {
        for (i in list) {
            if (!i.cart_item_delivery_price.equals("") && i.cart_item_delivery_price != null) {
                totalDeliveryPrice += i.cart_item_delivery_price.toInt()
            } else {
                continue
            }
        }
        txtTotalDeliveryPrice.text = "AED $totalDeliveryPrice"
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.cart_list)
        list = ArrayList()
        db = DbUtils(this)
        txtTotalPrice = findViewById(R.id.txt_total)
        txtTotalDeliveryPrice = findViewById(R.id.txt_delivery_total)
        companyID = intent!!.extras!!.getString("company_id")!!
    }

}
