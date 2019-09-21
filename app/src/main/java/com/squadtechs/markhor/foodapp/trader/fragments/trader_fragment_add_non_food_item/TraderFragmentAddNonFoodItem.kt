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
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.main_utils.MainUtils
import com.squadtechs.markhor.foodapp.trader.activity_trader_main.TraderMainCallBack
import org.json.JSONObject

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
    private lateinit var checkBoxZeroToSix: CheckBox //done
    private lateinit var checkBoxSixYears: CheckBox //done
    private lateinit var checkBoxSixToEight: CheckBox //done
    private lateinit var checkBoxEightYears: CheckBox //done
    private lateinit var checkBoxEightToTwelve: CheckBox //done
    private lateinit var checkBoxTenYears: CheckBox //done
    private lateinit var checkBoxTwoYears: CheckBox //done
    private lateinit var checkBoxTwelveYears: CheckBox //done
    private lateinit var checkBoxFourYears: CheckBox //done
    private lateinit var checkBoxFourteenYears: CheckBox //done
    private lateinit var checkBoxXSmall: CheckBox //done
    private lateinit var checkBoxSmall: CheckBox //done
    private lateinit var checkBoxMedium: CheckBox //done
    private lateinit var checkBoxLarge: CheckBox //done
    private lateinit var checkBoxXLarge: CheckBox //done
    private lateinit var checkBoxOneSize: CheckBox
    //CHECK_BOXES END
    private lateinit var obj: TraderMainCallBack
    private lateinit var doYouDeliver: String
    private lateinit var edtDeliveryPrice: EditText
    private lateinit var edtPrice: EditText
    private lateinit var edtProductName: EditText
    private lateinit var edtDescription: EditText
    private var listIteamAs: String = "Women Fashion"
    private var size: String? = null
    private var deliveryPrice: String = ""

    private lateinit var API: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.trader_fragment_add_non_food_item, container, false)
        initViews()
        populateListItemAsSpinner()
        populateSizeSpinner()
        setListeners()
        setCheckBoxListeners()
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
        } else {
            size = null
        }
        sendData()
    }

    private fun sendData() {
        if (activity!!.getSharedPreferences(
                "add_item_preferences",
                Context.MODE_PRIVATE
            ).getBoolean(
                "is_edit",
                false
            )
        ) {
            API = "http://squadtechsolution.com/android/v1/update_nonFood.php"
        } else {
            API = "http://squadtechsolution.com/android/v1/non_food_item.php"
        }
        val price = edtPrice.text.toString().trim()
        deliveryPrice = edtDeliveryPrice.text.toString()
        val description = edtDescription.text.toString().trim()
        val title = edtProductName.text.toString().trim()
        val requestQueue = Volley.newRequestQueue(activity!!)

        if (size != null && !price.equals("") && !description.equals("") && !price.equals("")) {

            val pd = MainUtils.getLoadingDialog(activity!!, "Adding", "Please wait", false)
            pd.show()

            val stringRequest = object : StringRequest(
                Method.POST,
                API,
                Response.Listener { response ->
                    pd.cancel()
                    Log.i("dxdiag", response)
                    try {
                        val json = JSONObject(response)
                        if (json.getString("status").equals("Item Uploaded ")) {
                            val pref =
                                activity!!.getSharedPreferences("item_id", Context.MODE_PRIVATE)
                            val editor = pref.edit()
                            editor.putString("value", json.getInt("non_food_id").toString())
                            editor.apply()
                            obj.onFragmentTap("images")
                        } else {
                            Toast.makeText(activity!!, "There was an error", Toast.LENGTH_LONG)
                                .show()
                        }
                    } catch (exc: Exception) {

                    }
                },
                Response.ErrorListener { error ->
                    pd.cancel()
                    Toast.makeText(activity!!, error.toString(), Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): MutableMap<String, String> {
                    val map = HashMap<String, String>()
                    map["title"] = title
                    map["description"] = description
                    map["price"] = price
                    map["list_item_as"] = listIteamAs
                    map["delivery_price"] = deliveryPrice
                    map["size"] = size!!
                    map["trader_id"] = activity!!.getSharedPreferences(
                        "user_credentials",
                        Context.MODE_PRIVATE
                    ).getString("id", "na") as String
                    Log.i("dxdiag", "\n\n\n${map}\n\n\n$API")
                    return map
                }
            }
            requestQueue.add(stringRequest)
        } else {
            Toast.makeText(activity!!, "Invalid credentials", Toast.LENGTH_LONG).show()
        }
    }

    private fun setListeners() {
        btnNext.setOnClickListener {
            prepareData()
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

    private fun setCheckBoxListeners() {

        checkBoxXLarge.setOnClickListener {
            if (checkBoxXLarge.isChecked) {
                checkBoxZeroToSix.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightYears.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxOneSize.isChecked = false
            }
        }

        checkBoxSixYears.setOnClickListener {
            if (checkBoxSixYears.isChecked) {
                checkBoxSixToEight.isChecked = false
                checkBoxEightYears.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxLarge.setOnClickListener {
            if (checkBoxLarge.isChecked) {
                checkBoxZeroToSix.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightYears.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
            }
        }

        checkBoxEightYears.setOnClickListener {
            if (checkBoxEightYears.isChecked) {
                checkBoxSixToEight.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxSixToEight.setOnClickListener {
            if (checkBoxSixToEight.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxEightToTwelve.setOnClickListener {
            if (checkBoxEightToTwelve.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxZeroToSix.setOnClickListener {
            if (checkBoxZeroToSix.isChecked) {
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightYears.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
            }
        }

        checkBoxMedium.setOnClickListener {
            if (checkBoxMedium.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxXSmall.setOnClickListener {
            if (checkBoxXSmall.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxSmall.setOnClickListener {
            if (checkBoxSmall.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxFourteenYears.setOnClickListener {
            if (checkBoxFourteenYears.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxFourYears.setOnClickListener {
            if (checkBoxFourYears.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxTwelveYears.setOnClickListener {
            if (checkBoxTwelveYears.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxTwoYears.setOnClickListener {
            if (checkBoxTwoYears.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxTenYears.setOnClickListener {
            if (checkBoxTenYears.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxOneSize.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }

        checkBoxOneSize.setOnClickListener {
            if (checkBoxOneSize.isChecked) {
                checkBoxEightYears.isChecked = false
                checkBoxSixYears.isChecked = false
                checkBoxSixToEight.isChecked = false
                checkBoxEightToTwelve.isChecked = false
                checkBoxTwoYears.isChecked = false
                checkBoxTwelveYears.isChecked = false
                checkBoxFourYears.isChecked = false
                checkBoxFourteenYears.isChecked = false
                checkBoxXSmall.isChecked = false
                checkBoxSmall.isChecked = false
                checkBoxMedium.isChecked = false
                checkBoxTenYears.isChecked = false
                checkBoxXLarge.isChecked = false
                checkBoxLarge.isChecked = false
                checkBoxZeroToSix.isChecked = false
            }
        }
    }

}
