package com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_companies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.ActivityCustomerMain

class ActivityCustomerNonFoodCompanies : AppCompatActivity() {

    private lateinit var txtTitle: TextView
    private lateinit var txtSubTitle: TextView
    private lateinit var txtAll: TextView
    private lateinit var txtWomen: TextView
    private lateinit var txtMen: TextView
    private lateinit var txtChildren: TextView
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: NonFoodCompaniesPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_non_food_companies)
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        txtTitle = findViewById(R.id.txt_title)
        txtSubTitle = findViewById(R.id.txt_subtitle)
        txtAll = findViewById(R.id.txt_all)
        txtWomen = findViewById(R.id.txt_women)
        txtMen = findViewById(R.id.txt_men)
        txtChildren = findViewById(R.id.txt_children)
        viewPager = findViewById(R.id.my_view_pager)
        pagerAdapter = NonFoodCompaniesPagerAdapter(
            intent!!.extras!!.get("position") as Int,
            supportFragmentManager
        )
        viewPager.adapter = pagerAdapter
        populateViewPager()

        when (intent!!.extras!!.get("position") as Int) {
            0 -> {
                txtTitle.text = "Clothes"
                txtSubTitle.text = "Choose your next outfit"
            }
        }
    }

    private fun populateViewPager() {
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
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityCustomerMain::class.java))
        finish()
    }

}
