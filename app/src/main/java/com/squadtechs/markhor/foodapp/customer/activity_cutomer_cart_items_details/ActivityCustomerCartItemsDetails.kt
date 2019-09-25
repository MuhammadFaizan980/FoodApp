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
    private lateinit var txtDeliverToMe: TextView
    private lateinit var txtIWillCollect: TextView
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
            txtDeliverToMe.setBackgroundResource(R.drawable.tab_back_selected)
            txtIWillCollect.setBackgroundResource(R.drawable.tab_back_unselected)
        }
        txtIWillCollect.setOnClickListener {
            txtDeliverToMe.setBackgroundResource(R.drawable.tab_back_unselected)
            txtIWillCollect.setBackgroundResource(R.drawable.tab_back_selected)
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
        if (count != 0  )
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
    }

}
