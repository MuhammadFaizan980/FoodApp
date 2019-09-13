package com.squadtechs.markhor.foodapp.customer.Fragments.fragment_food


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R

class CustomerFragmentFood : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.customer_fragmetn_food, container, false)
    }


}
