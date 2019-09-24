package com.squadtechs.markhor.foodapp.customer.activity_cutomer_cart_items_details

import android.os.Bundle
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
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.cart_list)
        list = ArrayList()
        db = DbUtils(this)
        companyID = intent!!.extras!!.getString("company_id")!!
    }

}
