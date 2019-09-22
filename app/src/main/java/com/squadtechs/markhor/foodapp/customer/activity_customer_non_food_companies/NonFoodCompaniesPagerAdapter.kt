package com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_companies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.customer.Fragments.fragment_customer_non_food_companies.FragmentCustomerNonFoodCompanies

class NonFoodCompaniesPagerAdapter(private val companyPosition: Int, manager: FragmentManager) :
    FragmentStatePagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        val fragment = FragmentCustomerNonFoodCompanies()
        val bundle = Bundle()
        bundle.putInt("tab_position", position)
        bundle.putInt("company_position", companyPosition)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = 4
}