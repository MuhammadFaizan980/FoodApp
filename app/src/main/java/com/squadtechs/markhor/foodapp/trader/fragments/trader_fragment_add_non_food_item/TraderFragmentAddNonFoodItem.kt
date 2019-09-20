package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_non_food_item


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R

class TraderFragmentAddNonFoodItem : Fragment() {

    private lateinit var spinnerSizes: Spinner
    private lateinit var spinnerListItemAs: Spinner
    private lateinit var mView: View
    private lateinit var linearDeliveryPrice: LinearLayout
    private lateinit var linearXsXl: LinearLayout
    private lateinit var linearChildren: LinearLayout
    private lateinit var linearOneSize: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_add_non_food_item, container, false)
        initViews()
        populateListItemAsSpinner()
        populateSizeSpinner()
        return mView
    }


    private fun populateSizeSpinner() {
        val arr = arrayOfNulls<String>(3)
        arr[0] = "XS - XL"
        arr[1] = "Children"
        arr[2] = "One Size"
        val arrayAdapter = ArrayAdapter<String>(
            activity!!,
            R.layout.list_dish_as_spinner_row,
            R.id.txt_list_dish_as_row_design,
            arr
        )
        spinnerSizes.adapter = arrayAdapter
        spinnerSizes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2.equals(0)) {
                    linearXsXl.visibility = View.VISIBLE
                    linearChildren.visibility = View.GONE
                    linearOneSize.visibility = View.GONE
                } else if (p2.equals(1)) {
                    linearXsXl.visibility = View.GONE
                    linearChildren.visibility = View.VISIBLE
                    linearOneSize.visibility = View.GONE
                } else {
                    linearXsXl.visibility = View.GONE
                    linearChildren.visibility = View.GONE
                    linearOneSize.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun populateListItemAsSpinner() {
        val arr = arrayOfNulls<String>(3)
        arr[0] = "Women Fashion"
        arr[1] = "Men Fashion"
        arr[2] = "Children"
        val arrayAdapter = ArrayAdapter<String>(
            activity!!,
            R.layout.list_dish_as_spinner_row,
            R.id.txt_list_dish_as_row_design,
            arr
        )
        spinnerListItemAs.adapter = arrayAdapter
    }

    private fun initViews() {
        spinnerListItemAs = mView.findViewById(R.id.spinner_list_item_as)
        spinnerSizes = mView.findViewById(R.id.spinner_size)
        linearDeliveryPrice = mView.findViewById(R.id.linear_delivery_price)
        linearXsXl = mView.findViewById(R.id.linear_xs_xl)
        linearChildren = mView.findViewById(R.id.linear_children)
        linearOneSize = mView.findViewById(R.id.linear_one_size)
    }

}
