package com.squadtechs.markhor.foodapp.customer.activity_customer_clothes

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_clothes.FragmentCustomerClothes

class ClothesPagerAdapter(private val list: ArrayList<String>, manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        val fragment = FragmentCustomerClothes()
        val bundle = Bundle()
        bundle.putInt("position", position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = list.size
}