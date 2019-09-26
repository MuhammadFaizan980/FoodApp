package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_orders.FragmentOrderMain

class TraderOrderPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager)  {
    override fun getItem(position: Int): Fragment {
        val fragment = TraderFragmentOrderMain()
        val bundle = Bundle()
        bundle.putInt("key", position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = 2
}