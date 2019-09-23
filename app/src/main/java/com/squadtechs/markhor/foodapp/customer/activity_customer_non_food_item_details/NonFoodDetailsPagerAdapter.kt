package com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_item_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.customer.Fragments.fragment_image.FragmentImage

class NonFoodDetailsPagerAdapter(private val list: Array<String?>, manager: FragmentManager) :
    FragmentStatePagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        val fragment = FragmentImage()
        val bundle = Bundle()
        bundle.putString("url", list[position])
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = list.size
}