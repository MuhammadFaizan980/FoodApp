package com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_item_details

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squadtechs.markhor.foodapp.CONSTANTS
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.customer_add_to_cart_sheet.CustomerAddToCartSheet

class ActivityCustomerNonFoodItemDetails : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var txtFirst: TextView
    private lateinit var txtSecond: TextView
    private lateinit var txtThird: TextView
    private lateinit var txtTitle: TextView
    private lateinit var txtPrice: TextView
    private lateinit var txtDescription: TextView
    private lateinit var fabAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_non_food_item_details)
        initViews()
        populateViewPager()
        populateViews()
        setFabListener()
    }

    private fun setFabListener() {
        fabAdd.setOnClickListener {
            val pref = getSharedPreferences("bc", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(
                "cart_item_url",
                "${CONSTANTS.imgPre}${intent!!.extras!!.getString("image1")}"
            )
            editor.putString("cart_item_title", intent!!.extras!!.getString("title"))
            editor.putString("cart_item_description", intent!!.extras!!.getString("description"))
            editor.putString("cart_item_price", intent!!.extras!!.getString("price"))
            editor.putString(
                "cart_item_delivery_price",
                intent!!.extras!!.getString("delivery_price")
            )
            editor.putString("company_id", intent!!.extras!!.getString("company_id"))
            editor.putString("item_id", intent!!.extras!!.getString("item_id"))
            editor.putString("is_food", "no")
            editor.putString(
                "customer_id", getSharedPreferences(
                    "user_credentials",
                    Context.MODE_PRIVATE
                ).getString("id", "none")
            )
            editor.apply()
            val bottomSheet = CustomerAddToCartSheet()
            bottomSheet.show(supportFragmentManager, "lol")
        }
    }

    private fun populateViews() {
        txtTitle.text = intent!!.extras!!.getString("title")
        txtPrice.text = "AED ${intent!!.extras!!.getString("price")}"
        txtDescription.text = intent!!.extras!!.getString("description")
    }

    private fun populateViewPager() {
        val arr = arrayOfNulls<String>(3)
        arr[0] = intent!!.extras!!.getString("image1")
        arr[1] = intent!!.extras!!.getString("image2")
        arr[2] = intent!!.extras!!.getString("image3")
        val adapter = NonFoodDetailsPagerAdapter(arr, supportFragmentManager)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
                        txtFirst.setBackgroundResource(R.drawable.slider_selected)
                        txtSecond.setBackgroundResource(R.drawable.slider_un_selected)
                        txtThird.setBackgroundResource(R.drawable.slider_un_selected)
                    }
                    1 -> {
                        txtFirst.setBackgroundResource(R.drawable.slider_un_selected)
                        txtSecond.setBackgroundResource(R.drawable.slider_selected)
                        txtThird.setBackgroundResource(R.drawable.slider_un_selected)
                    }
                    2 -> {
                        txtFirst.setBackgroundResource(R.drawable.slider_un_selected)
                        txtSecond.setBackgroundResource(R.drawable.slider_un_selected)
                        txtThird.setBackgroundResource(R.drawable.slider_selected)
                    }
                }
            }
        })
    }

    private fun initViews() {
        viewPager = findViewById(R.id.view_pager)
        txtFirst = findViewById(R.id.first)
        txtSecond = findViewById(R.id.second)
        txtThird = findViewById(R.id.third)
        txtTitle = findViewById(R.id.txt_title)
        txtPrice = findViewById(R.id.txt_price)
        txtDescription = findViewById(R.id.txt_description)
        fabAdd = findViewById(R.id.add_to_cart)
    }

}
