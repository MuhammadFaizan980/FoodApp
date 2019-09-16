package com.squadtechs.markhor.foodapp.customer.activity_food_company_information

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.customer.Fragments.fragment_food.CustomerFragmentFood

class CompanyInformationPagerAdapter(manager: FragmentManager) :
    FragmentStatePagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        val fragment =
            CustomerFragmentFood()
        val bundle = Bundle()
        bundle.putInt("key", position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = 5
}