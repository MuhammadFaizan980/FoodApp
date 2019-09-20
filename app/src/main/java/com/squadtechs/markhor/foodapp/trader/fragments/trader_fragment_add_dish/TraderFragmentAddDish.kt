package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_dish


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R

class TraderFragmentAddDish : Fragment() {

    private lateinit var spinnerListDishAs: Spinner
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_add_dish, container, false)
        initViews()
        populateSpinner()
        return mView
    }

    private fun populateSpinner() {
        val arr = arrayOfNulls<String>(4)
        arr[0] = "Main"
        arr[1] = "Side"
        arr[2] = "Desert"
        arr[3] = "Starters"
        val arrayAdapter = ArrayAdapter<String>(
            activity!!,
            R.layout.list_dish_as_spinner_row,
            R.id.txt_list_dish_as_row_design,
            arr
        )
        spinnerListDishAs.adapter = arrayAdapter
    }

    private fun initViews() {
        spinnerListDishAs = mView.findViewById(R.id.spinner_list_dish_as)
    }

}
