package com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_companies_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_non_food.FragmentCustomerNonFood

class ClothesPagerAdapter(
    private val companyID: String,
    private val companyType: String,
    private val tabCount: Int,
    manager: FragmentManager
) :
    FragmentStatePagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        val fragment = FragmentCustomerNonFood()
        val bundle = Bundle()
        bundle.putInt("position", position)
        bundle.putString("company_id", companyID)
        bundle.putString("company_type", companyType)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = tabCount
}