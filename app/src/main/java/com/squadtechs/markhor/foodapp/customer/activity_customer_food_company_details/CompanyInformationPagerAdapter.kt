package com.squadtechs.markhor.foodapp.customer.activity_customer_food_company_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_food.CustomerFragmentFood

class CompanyInformationPagerAdapter(manager: FragmentManager, private val companyID: String) :
    FragmentStatePagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        val fragment =
            CustomerFragmentFood()
        val bundle = Bundle()
        bundle.putInt("key", position)
        bundle.putString("company_id", companyID)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = 5
}