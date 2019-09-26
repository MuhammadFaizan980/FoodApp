package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_end_of_cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_main.CustomerFoodFragmetnCallback

class CustomerFragmentEndOfCart : Fragment() {

    private lateinit var obj: CustomerFoodFragmetnCallback
    private lateinit var btnTrackOrder: Button
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.customer_fragment_end_of_cart, container, false)
        initViews()
        setListeners()
        return mView
    }

    private fun setListeners() {
        btnTrackOrder.setOnClickListener {
            obj.onTrackOrderPressed()
        }
    }

    private fun initViews() {
        btnTrackOrder = mView.findViewById(R.id.btn_track_order)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        obj = context as CustomerFoodFragmetnCallback
    }

}
