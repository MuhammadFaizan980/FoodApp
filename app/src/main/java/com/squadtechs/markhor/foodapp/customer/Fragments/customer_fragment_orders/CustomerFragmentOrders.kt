package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.squadtechs.markhor.foodapp.R

class CustomerFragmentOrders : Fragment() {
    private lateinit var mView: View
    private lateinit var txtCurrentOrders: TextView
    private lateinit var txtPastOrders: TextView
    private lateinit var viewPager: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.customer_fragment_orders, container, false)
        initViews()
        setUpViewPager()
        return mView
    }

    private fun setUpViewPager() {
        val adapter = OrderPagerAdapter(childFragmentManager)
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
                        txtCurrentOrders.setBackgroundResource(R.drawable.tab_back_selected)
                        txtPastOrders.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    1 -> {
                        txtCurrentOrders.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtPastOrders.setBackgroundResource(R.drawable.tab_back_selected)
                    }
                }
            }

        })
    }

    private fun initViews() {
        txtCurrentOrders = mView.findViewById(R.id.txt_current_orders)
        txtPastOrders = mView.findViewById(R.id.txt_past_orders)
        viewPager = mView.findViewById(R.id.pager)
    }

}
