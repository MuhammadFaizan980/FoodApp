package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragmetn_food.TraderFragmentFood

class TraderHomePagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        val fragment =
            TraderFragmentFood()
        val bundle = Bundle()
        bundle.putInt("key", position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = 5
}