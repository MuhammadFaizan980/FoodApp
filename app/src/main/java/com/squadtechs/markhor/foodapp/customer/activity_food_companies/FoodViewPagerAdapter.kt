package com.squadtechs.markhor.foodapp.customer.activity_food_companies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.customer.Fragments.fragment_inside_food.FragmentInsideFood

class FoodViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        val fragmentInFood = FragmentInsideFood()
        val bundle = Bundle()
        bundle.putInt("key", position)
        fragmentInFood.arguments = bundle
        return fragmentInFood
    }

    override fun getCount(): Int = 3

}