package com.squadtechs.markhor.foodapp.customer.customer_fragment_orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R

class CustomerFragmentOrders : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.customer_fragment_orders, container, false)
    }
}
