package com.squadtechs.markhor.foodapp.customer.activity_customer_clothes_companies_details

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.squadtechs.markhor.foodapp.R

class ActivityCustomerClothesCompaniesDetails : AppCompatActivity() {
    private lateinit var viewPackage: ViewPager
    private lateinit var txtAll: TextView
    private lateinit var txtWomen: TextView
    private lateinit var txtMen: TextView
    private lateinit var txtChildren: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_clothes_companies_details)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        val list = ArrayList<String>()
        list.add("All")
        list.add("Women")
        list.add("Men")
        list.add("Children")
        viewPackage.adapter =
            ClothesPagerAdapter(
                list,
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
    }

    private fun initViews() {
        viewPackage = findViewById(R.id.clothes_view_pager)
        txtAll = findViewById(R.id.txt_all)
        txtWomen = findViewById(R.id.txt_women)
        txtMen = findViewById(R.id.txt_men)
        txtChildren = findViewById(R.id.txt_children)
    }

}
