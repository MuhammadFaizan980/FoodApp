package com.squadtechs.markhor.foodapp.customer.customer_add_to_cart_sheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.makeramen.roundedimageview.RoundedImageView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.db_utiils.CartUtil
import com.squadtechs.markhor.foodapp.customer.db_utiils.DbUtils
import com.squareup.picasso.Picasso

class CustomerAddToCartSheet : BottomSheetDialogFragment() {
    private lateinit var mView: View
    private lateinit var txtTitle: TextView
    private lateinit var txtDescription: TextView
    private lateinit var txtPrice: TextView
    private lateinit var txtItemCount: TextView
    private lateinit var itemImage: RoundedImageView
    private lateinit var imgIncrease: ImageView
    private lateinit var imgDecrease: ImageView
    private lateinit var btnAddToBasket: Button
    private var itemCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_customer_add_to_cart_sheet, container, false)
        initViews()
        setListeners()
        return mView
    }

    private fun setListeners() {
        imgIncrease.setOnClickListener {
            itemCount = txtItemCount.text.toString().toInt()
            itemCount++
            txtItemCount.text = "$itemCount"
        }
        imgDecrease.setOnClickListener {
            if (itemCount > 0) {
                itemCount--
                txtItemCount.text = "$itemCount"
            }
        }
        val pref = activity!!.getSharedPreferences("bc", Context.MODE_PRIVATE)
        Picasso.get().load(
            activity!!.getSharedPreferences(
                "bc",
                Context.MODE_PRIVATE
            ).getString("cart_item_url", "none")
        ).into(itemImage)
        txtTitle.text = pref.getString("cart_item_title", "none")
        txtDescription.text = pref.getString("cart_item_description", "none")
        txtPrice.text = "AED " + pref.getString("cart_item_price", "none")

        btnAddToBasket.setOnClickListener {
            if (itemCount > 0) {
                val obj = CartUtil()
                obj.cart_item_title = pref.getString("cart_item_title", "none").toString()
                obj.cart_item_quantity = itemCount.toString()
                obj.cart_item_price = pref.getString("cart_item_price", "none").toString()
                obj.item_id = pref.getString("item_id", "none").toString()
                obj.cart_item_delivery_price =
                    pref.getString("cart_item_delivery_price", "none").toString()
                obj.company_id = pref.getString("company_id", "none").toString()
                obj.is_food = pref.getString("is_food", "none").toString()
                obj.customer_id = pref.getString("customer_id", "none").toString()
                val db = DbUtils(activity!!)
                db.insertData(obj)
                dismiss()
            } else {
                Toast.makeText(activity!!, "Item count cannot be 0", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViews() {
        txtTitle = mView.findViewById(R.id.txt_title)
        txtDescription = mView.findViewById(R.id.txt_description)
        txtPrice = mView.findViewById(R.id.txt_price)
        txtItemCount = mView.findViewById(R.id.txt_item_count)
        itemImage = mView.findViewById(R.id.img_item)
        imgIncrease = mView.findViewById(R.id.img_positive)
        imgDecrease = mView.findViewById(R.id.img_negative)
        btnAddToBasket = mView.findViewById(R.id.btn_add_to_basket)
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

}
