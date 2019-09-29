package com.squadtechs.markhor.foodapp.customer.activity_customer_edit_address

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_address.ActivityCustomerAddress
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.ActivityCustomerMain
import com.squadtechs.markhor.foodapp.main_utils.MainUtils

class ActivityCustomerEditAddress : AppCompatActivity() {

    private lateinit var imgGoBack: ImageView
    private lateinit var btnSaveAddress: Button
    private lateinit var edtAddess: EditText
    private lateinit var addressCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_edit_address)
        initViews()
        setListeners()
    }


    private fun setListeners() {
        imgGoBack.setOnClickListener {
            startActivity(Intent(this, ActivityCustomerAddress::class.java))
            finish()
        }
        btnSaveAddress.setOnClickListener {
            val address = edtAddess.text.toString().trim()
            val isDefault = if (addressCheckBox.isChecked) {
                "Yes"
            } else {
                "No"
            }
            val address_id = intent!!.extras!!.get("address_id") as String
            val customer_id =
                getSharedPreferences("user_credentials", Context.MODE_PRIVATE).getString(
                    "id",
                    "none"
                ) as String

            if (address.equals("") || isDefault.equals("") || customer_id.equals("none")) {
                Toast.makeText(this, "All fields must be filled properly", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val pd = MainUtils.getLoadingDialog(this, "Editing", "Please wait", false)
                pd.show()
                val API = "http://squadtechsolution.com//android/v1/update_customer_address.php"
                val requestQueue = Volley.newRequestQueue(this)
                val stringRequest = object : StringRequest(
                    Method.POST,
                    API,
                    Response.Listener { response ->
                        pd.cancel()
                        Log.i("dxdiag", response)
                        if (response.contains("Address Updated")) {
                            val pref =
                                getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
                            val editor = pref.edit()
                            editor.putString("default_address", address)
                            editor.apply()
                            Toast.makeText(this, "Address updated successfully", Toast.LENGTH_LONG)
                                .show()
                            startActivity(Intent(this, ActivityCustomerMain::class.java))
                            finish()
                        } else if (response.contains("You must make Atleast one Address As_default")) {
                            Toast.makeText(
                                this,
                                "At least one address must be made default",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                "There was an error updating you address",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    Response.ErrorListener { error ->
                        pd.cancel()
                        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
                    }) {
                    override fun getParams(): MutableMap<String, String> {
                        val map = HashMap<String, String>()
                        map["id"] = address_id
                        map["address"] = address
                        map["isDefault"] = isDefault
                        map["customer_id"] = customer_id
                        return map
                    }
                }
                requestQueue.add(stringRequest)
            }
        }
    }

    private fun initViews() {
        imgGoBack = findViewById(R.id.img_go_back)
        edtAddess = findViewById(R.id.edt_customer_address)
        btnSaveAddress = findViewById(R.id.btn_save_customer_address)
        addressCheckBox = findViewById(R.id.check_box_customer_address)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityCustomerAddress::class.java))
        finish()
    }

}
