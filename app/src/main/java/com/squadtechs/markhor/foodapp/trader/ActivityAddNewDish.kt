package com.squadtechs.markhor.foodapp.trader

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R

class ActivityAddNewDish : AppCompatActivity() {

    private lateinit var spinnerListDishAs: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_dish)
        initViews()
        populateSpinner()
    }

    private fun populateSpinner() {
        val arr = arrayOfNulls<String>(4)
        arr[0] = "Main"
        arr[1] = "Side"
        arr[2] = "Desert"
        arr[3] = "Starters"
        val arrayAdapter = ArrayAdapter<String>(
            this,
            R.layout.list_dish_as_spinner_row,
            R.id.txt_list_dish_as_row_design,
            arr
        )
        spinnerListDishAs.adapter = arrayAdapter
    }

    private fun initViews() {
        spinnerListDishAs = findViewById(R.id.spinner_list_dish_as)
    }

}
