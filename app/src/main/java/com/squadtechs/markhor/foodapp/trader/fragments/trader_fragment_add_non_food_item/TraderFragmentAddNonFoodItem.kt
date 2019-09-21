package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_non_food_item

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.TraderMainCallBack

class TraderFragmentAddNonFoodItem : Fragment() {

    private lateinit var spinnerSizes: Spinner
    private lateinit var spinnerListItemAs: Spinner
    private lateinit var mView: View
    private lateinit var btnNext: Button
    private lateinit var linearDeliveryPrice: LinearLayout
    private lateinit var linearXsXl: LinearLayout
    private lateinit var linearChildren: LinearLayout
    private lateinit var linearOneSize: LinearLayout
    //CHECK_BOXES
    private lateinit var checkBoxZeroToSix: CheckBox
    private lateinit var checkBoxSixYears: CheckBox
    private lateinit var checkBoxSixToEight: CheckBox
    private lateinit var checkBoxEightYears: CheckBox
    private lateinit var checkBoxEightToTwelve: CheckBox
    private lateinit var checkBoxTenYears: CheckBox
    private lateinit var checkBoxTwoYears: CheckBox
    private lateinit var checkBoxTwelveYears: CheckBox
    private lateinit var checkBoxFourYears: CheckBox
    private lateinit var checkBoxFourteenYears: CheckBox
    private lateinit var checkBoxXSmall: CheckBox
    private lateinit var checkBoxSmall: CheckBox
    private lateinit var checkBoxMedium: CheckBox
    private lateinit var checkBoxLarge: CheckBox
    private lateinit var checkBoxXLarge: CheckBox
    private lateinit var checkBoxOneSize: CheckBox
    //CHECK_BOXES END
    private lateinit var obj: TraderMainCallBack
    private lateinit var doYouDeliver: String
    private lateinit var edtDeliveryPrice: EditText
    private lateinit var edtPrice: EditText
    private lateinit var edtProductName: EditText
    private lateinit var edtDescription: EditText
    private var listIteamAs: String = "Women Fashion"
    private lateinit var size: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_add_non_food_item, container, false)
        initViews()
        populateListItemAsSpinner()
        populateSizeSpinner()
        setListeners()
        return mView
    }

    private fun prepareData() {
        if (checkBoxZeroToSix.isChecked) {
            size = "For 0-6 months old"
        } else if (checkBoxSixYears.isChecked) {
            size = "For 6 years old"
        } else if (checkBoxSixToEight.isChecked) {
            size = "For 6-8 months old"
        } else if (checkBoxEightYears.isChecked) {
            size = "For 8 years old"
        } else if (checkBoxEightToTwelve.isChecked) {
            size = "For 8-12 months old"
        } else if (checkBoxTenYears.isChecked) {
            size = "For 10 years old"
        } else if (checkBoxTwoYears.isChecked) {
            size = "For 2 years old"
        } else if (checkBoxTwelveYears.isChecked) {
            size = "For 12 years old"
        } else if (checkBoxFourYears.isChecked) {
            size = "For 4 years old"
        } else if (checkBoxFourteenYears.isChecked) {
            size = "For 14 years old"
        } else if (checkBoxXSmall.isChecked) {
            size = "x-small"
        } else if (checkBoxSmall.isChecked) {
            size = "small"
        } else if (checkBoxMedium.isChecked) {
            size = "medium"
        } else if (checkBoxLarge.isChecked) {
            size = "large"
        } else if (checkBoxXLarge.isChecked) {
            size = "x-large"
        } else if (checkBoxOneSize.isChecked) {
            size = "one size"
        }
        sendData()
    }

    private fun sendData() {
        Log.i("dxdiag", size + "\n")
        Log.i("dxdiag", edtPrice.text.toString() + "\n")
        Log.i("dxdiag", edtDeliveryPrice.text.toString() + "\n")
        Log.i("dxdiag", edtDescription.text.toString() + "\n")
        Log.i("dxdiag", edtProductName.text.toString() + "\n")
        Log.i("dxdiag", listIteamAs)
    }

    private fun setListeners() {
        btnNext.setOnClickListener {
            prepareData()
            obj.onFragmentTap("images")
        }
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

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) =
                when (p2) {
                    0 -> {
                        linearXsXl.visibility = View.VISIBLE
                        linearChildren.visibility = View.GONE
                        linearOneSize.visibility = View.GONE
                    }
                    1 -> {
                        linearXsXl.visibility = View.GONE
                        linearChildren.visibility = View.VISIBLE
                        linearOneSize.visibility = View.GONE
                    }
                    else -> {
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
        spinnerListItemAs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listIteamAs = arr[p2] as String
            }

        }
    }

    private fun initViews() {
        spinnerListItemAs = mView.findViewById(R.id.spinner_list_item_as)
        spinnerSizes = mView.findViewById(R.id.spinner_size)
        linearDeliveryPrice = mView.findViewById(R.id.linear_delivery_price)
        linearXsXl = mView.findViewById(R.id.linear_xs_xl)
        linearChildren = mView.findViewById(R.id.linear_children)
        linearOneSize = mView.findViewById(R.id.linear_one_size)
        btnNext = mView.findViewById(R.id.btn_next)
        doYouDeliver = activity!!.getSharedPreferences(
            "user_credentials",
            Context.MODE_PRIVATE
        ).getString("delivery_type", "none") as String
        if (!doYouDeliver.equals("yes")) {
            linearDeliveryPrice.visibility = View.VISIBLE
        }

        checkBoxEightToTwelve = mView.findViewById(R.id.eight_to_twelve)
        checkBoxEightYears = mView.findViewById(R.id.eight_years)
        checkBoxFourYears = mView.findViewById(R.id.four_years)
        checkBoxFourteenYears = mView.findViewById(R.id.fourteen_years)
        checkBoxLarge = mView.findViewById(R.id.large)
        checkBoxMedium = mView.findViewById(R.id.medium)
        checkBoxOneSize = mView.findViewById(R.id.single)
        checkBoxSixToEight = mView.findViewById(R.id.six_eight_months)
        checkBoxSixYears = mView.findViewById(R.id.six_years)
        checkBoxSmall = mView.findViewById(R.id.small)
        checkBoxZeroToSix = mView.findViewById(R.id.zero_six_months)
        checkBoxTenYears = mView.findViewById(R.id.ten_years)
        checkBoxTwoYears = mView.findViewById(R.id.two_years)
        checkBoxTwelveYears = mView.findViewById(R.id.twelve_years)
        checkBoxXSmall = mView.findViewById(R.id.x_small)
        checkBoxXLarge = mView.findViewById(R.id.x_large)

        edtProductName = mView.findViewById(R.id.edt_product_name)
        edtDescription = mView.findViewById(R.id.edt_product_description)
        edtDeliveryPrice = mView.findViewById(R.id.edt_dish_delivery_price)
        edtPrice = mView.findViewById(R.id.edt_product_price)

    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        obj = activity!! as TraderMainCallBack
    }

}
