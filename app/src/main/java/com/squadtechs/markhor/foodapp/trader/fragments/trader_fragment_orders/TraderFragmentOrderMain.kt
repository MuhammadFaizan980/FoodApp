package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_orders


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R

class TraderFragmentOrderMain : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.trader_fragment_order_main, container, false)
    }


}
