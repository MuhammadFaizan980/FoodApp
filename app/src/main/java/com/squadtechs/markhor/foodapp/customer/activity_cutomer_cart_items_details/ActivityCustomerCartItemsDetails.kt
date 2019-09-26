package com.squadtechs.markhor.foodapp.customer.activity_cutomer_cart_items_details

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.SingletonQueue
import com.squadtechs.markhor.foodapp.customer.activity_customer_select_address.ActivityCustomerSelectAddress
import com.squadtechs.markhor.foodapp.customer.db_utiils.CartUtil
import com.squadtechs.markhor.foodapp.customer.db_utiils.DbUtils
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import org.json.JSONObject

class ActivityCustomerCartItemsDetails : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnPlaceOrder: Button
    private lateinit var edtRequest: EditText
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
    private var iteration: Int = 0
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
        iteration = (list.size - 1)
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

        btnPlaceOrder.setOnClickListener {
            if (edtRequest.text.toString().trim().equals("")) {
                val dialog = MainUtils.getMessageDialog(
                    this,
                    "Information!",
                    "Are you sure you want to proceed without any allergy request?",
                    false
                )
                dialog.setPositiveButton("Proceed") { dialogInterface, i ->
                    placeOrder(iteration)
                }
                    .setNegativeButton("Cancel") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                dialog.show()
            } else {
                placeOrder(iteration)
            }
        }

    }


    private fun placeOrder(itr: Int) {
        if (itr >= 0) {
            val API = "http://squadtechsolution.com/android/v1/add_order.php"
            val requestQueue = SingletonQueue.getRequestQueue(this)
            val stringRequestQueue = object : StringRequest(
                Method.POST,
                API,
                Response.Listener { response ->
                    Log.i("cart_response", response)
                    if (JSONObject(response).getString("status").equals(" Order Adding  Successfully")) {
                        iteration--
                        placeOrder(iteration)
                    } else {
                        Toast.makeText(this, "There was an error", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): MutableMap<String, String> {
                    val map = HashMap<String, String>()
                    map["customer_id"] = list[iteration].customer_id
                    map["company_id"] = list[iteration].company_id
                    map["request"] = edtRequest.text.toString().trim()
                    map["quantity"] = list[iteration].cart_item_quantity
                    map["status"] = "pending"
                    map["address"] = address
                    map["item_id"] = list[iteration].item_id
                    map["is_food"] = list[iteration].is_food
                    map["i_will_pick"] = deliverToMe
                    return map
                }
            }
            requestQueue.add(stringRequestQueue)
        } else {
            if (db.deleteCollection(companyID)) {
                setResult(Activity.RESULT_OK)
                finish()
            }
            return
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
        edtRequest = findViewById(R.id.edt_request)
        btnPlaceOrder = findViewById(R.id.btn_place_order)
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
