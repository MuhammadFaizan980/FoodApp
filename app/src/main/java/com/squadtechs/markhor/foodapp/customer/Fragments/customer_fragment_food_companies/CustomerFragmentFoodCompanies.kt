package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_food_companies


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.squadtechs.markhor.foodapp.R

class CustomerFragmentFoodCompanies : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var firstTab: TextView
    private lateinit var secondTab: TextView
    private lateinit var thirdTab: TextView
    private lateinit var mView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.customer_fragment_food_companies, container, false)
        initView()
        populateViewPager()
        return mView
    }

    private fun populateViewPager() {
        viewPager.adapter =
            FoodViewPagerAdapter(
                childFragmentManager
            )
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
                        firstTab.setBackgroundResource(R.drawable.tab_back_selected)
                        secondTab.setBackgroundResource(R.drawable.tab_back_unselected)
                        thirdTab.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    1 -> {
                        firstTab.setBackgroundResource(R.drawable.tab_back_unselected)
                        secondTab.setBackgroundResource(R.drawable.tab_back_selected)
                        thirdTab.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    2 -> {
                        firstTab.setBackgroundResource(R.drawable.tab_back_unselected)
                        secondTab.setBackgroundResource(R.drawable.tab_back_unselected)
                        thirdTab.setBackgroundResource(R.drawable.tab_back_selected)
                    }
                }

            }
        })
    }

    private fun initView() {
        viewPager = mView.findViewById(R.id.food_view_pager)
        firstTab = mView.findViewById(R.id.txt_first_tab)
        secondTab = mView.findViewById(R.id.txt_second_tab)
        thirdTab = mView.findViewById(R.id.txt_third_tab)
    }

}
