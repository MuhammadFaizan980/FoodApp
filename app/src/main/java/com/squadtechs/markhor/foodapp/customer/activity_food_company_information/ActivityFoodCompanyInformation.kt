package com.squadtechs.markhor.foodapp.customer.activity_food_company_information

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.squadtechs.markhor.foodapp.R

class ActivityFoodCompanyInformation : AppCompatActivity() {
    private lateinit var viewPackage: ViewPager
    private lateinit var txtAll: TextView
    private lateinit var txtStarters: TextView
    private lateinit var txtMain: TextView
    private lateinit var txtSides: TextView
    private lateinit var txtDeserts: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_company_information)
        initViews()
        setListeners()
    }

    private fun setListeners() {
        viewPackage.adapter =
            CompanyInformationPagerAdapter(supportFragmentManager)
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
                        txtStarters.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMain.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtSides.setBackgroundResource(R.drawable.tab_back_unselected)
                    }

                    1 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtStarters.setBackgroundResource(R.drawable.tab_back_selected)
                        txtMain.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtSides.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtDeserts.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    2 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtStarters.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMain.setBackgroundResource(R.drawable.tab_back_selected)
                        txtSides.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtDeserts.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    3 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtStarters.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMain.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtSides.setBackgroundResource(R.drawable.tab_back_selected)
                        txtDeserts.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    4 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtStarters.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMain.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtSides.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtDeserts.setBackgroundResource(R.drawable.tab_back_selected)
                    }
                }
            }

        })
    }

    private fun initViews() {
        viewPackage = findViewById(R.id.company_view_pager)
        txtAll = findViewById(R.id.txt_all)
        txtStarters = findViewById(R.id.txt_starters)
        txtMain = findViewById(R.id.txt_main_course)
        txtSides = findViewById(R.id.txt_sides)
        txtDeserts = findViewById(R.id.txt_deserts)
    }

}
