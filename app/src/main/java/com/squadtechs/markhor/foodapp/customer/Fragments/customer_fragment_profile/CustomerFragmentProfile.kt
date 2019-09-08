package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.squadtechs.markhor.foodapp.R

/**
 * A simple [Fragment] subclass.
 */
class CustomerFragmentProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.customer_fragment_profile, container, false)
    }


}
