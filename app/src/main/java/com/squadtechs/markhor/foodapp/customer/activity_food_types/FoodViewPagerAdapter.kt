package com.squadtechs.markhor.foodapp.customer.activity_food_types

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.squadtechs.markhor.foodapp.customer.Fragments.fragment_inside_food.FragmentInsideFood

class FoodViewPagerAdapter(
    private val list: ArrayList<String>,
    private val context: Context,
    private val manager: FragmentManager
) :
    FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        val fragmentInFood = FragmentInsideFood()
        val bundle = Bundle()
        bundle.putString("key", list[position])
        fragmentInFood.arguments = bundle
        return fragmentInFood
    }

    override fun getCount(): Int = list.size

}