package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_add_dish


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.squadtechs.markhor.foodapp.R

class TraderFragmentAddDish : Fragment() {

    private lateinit var spinnerListDishAs: Spinner
    private lateinit var mView: View
    private lateinit var linearDeliveryPrice: LinearLayout
    private lateinit var edtTitle: EditText
    private lateinit var edtDescription: EditText
    private lateinit var edtPrice: EditText
    private lateinit var edtDeliveryPrice: EditText
    private lateinit var imgDish: ImageView
    private var uri: Uri? = null
    private lateinit var doYouDeliver: String
    private var deliveryPrice: String = ""
    private var dishContains: String = ""
    private var listItemAs: String = "Main"

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
        spinnerListDishAs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listItemAs = arr[p2] as String
            }

        }
    }

    private fun initViews() {
        spinnerListDishAs = mView.findViewById(R.id.spinner_list_dish_as)
        linearDeliveryPrice = mView.findViewById(R.id.linear_delivery_price)
        doYouDeliver = activity!!.getSharedPreferences(
            "user_credentials",
            Context.MODE_PRIVATE
        ).getString("delivery_type", "none") as String
        if (doYouDeliver.equals("yes")) {
            linearDeliveryPrice.visibility = View.VISIBLE
        }
    }

}
