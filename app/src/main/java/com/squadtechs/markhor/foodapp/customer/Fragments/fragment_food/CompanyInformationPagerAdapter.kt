package com.squadtechs.markhor.foodapp.customer.Fragments.fragment_food

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CompanyInformationPagerAdapter(
    private val list: ArrayList<String>,
    manager: FragmentManager
) : FragmentStatePagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        val fragment = CustomerFragmentFood()
        val bundle = Bundle()
        bundle.putInt("key", position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = list.size
}