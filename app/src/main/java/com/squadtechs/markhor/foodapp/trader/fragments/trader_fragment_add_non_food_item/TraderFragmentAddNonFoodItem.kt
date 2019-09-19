package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_non_food_item


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.squadtechs.markhor.foodapp.R

/**
 * A simple [Fragment] subclass.
 */
class TraderFragmentAddNonFoodItem : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.trader_fragment_add_non_food_item,
            container,
            false
        )
    }


}
