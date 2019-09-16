package com.squadtechs.markhor.foodapp.customer.activity_customer_clothes_companies_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_clothes.FragmentCustomerClothes

class ClothesPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        val fragment = FragmentCustomerClothes()
        val bundle = Bundle()
        bundle.putInt("position", position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = 4
}