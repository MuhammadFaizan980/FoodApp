package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_clothes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R

class FragmentCustomerClothes : Fragment() {
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_customer_clothes, container, false)
        return mView
    }
}
