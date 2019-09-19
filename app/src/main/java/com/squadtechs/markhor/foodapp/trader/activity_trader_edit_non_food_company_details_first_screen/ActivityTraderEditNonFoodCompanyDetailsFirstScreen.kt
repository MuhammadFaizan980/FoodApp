package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_non_food_company_details_first_screen

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R

class ActivityTraderEditNonFoodCompanyDetailsFirstScreen : AppCompatActivity() {

    private lateinit var spinnerShopType: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_edit_non_food_company_details_first_screen)
        initViews()
        populateSpinner()
    }

    private fun populateSpinner() {
        val arr = arrayOfNulls<String>(8)
        arr[0] = "Clothes"
        arr[1] = "Accessories"
        arr[2] = "Skincare"
        arr[3] = "Homeware"
        arr[4] = "Toys"
        arr[5] = "Sheos"
        arr[6] = "Bags"
        arr[7] = "Other"
        val arrAdapter = ArrayAdapter<String>(
            this,
            R.layout.shop_type_spinner_row_design,
            R.id.txt_shop_type,
            arr
        )
        spinnerShopType.adapter = arrAdapter
    }

    private fun initViews() {
        spinnerShopType = findViewById(R.id.spinner_shop_type)
    }

}
