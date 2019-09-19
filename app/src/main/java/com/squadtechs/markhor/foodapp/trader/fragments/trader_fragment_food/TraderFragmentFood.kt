package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_food

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.squadtechs.markhor.foodapp.R

class TraderFragmentFood : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.trader_fragment_food, container, false)
    }


}
