package com.squadtechs.markhor.foodapp.customer.activity_cutomer_cart_items_details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.db_utiils.CartUtil

class CartItemsDetailsDetailsAdapter(
    private val list: ArrayList<CartUtil>,
    private val context: Context
) : RecyclerView.Adapter<CartItemsDetailsDetailsAdapter.CartDetailsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartDetailsHolder =
        CartDetailsHolder(
            LayoutInflater.from(context).inflate(
                R.layout.cart_details_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: CartDetailsHolder, position: Int) {
        val obj = list[position]
        holder.txtQuantity.text = "x${obj.cart_item_quantity}"
        holder.txtTitle.text = obj.cart_item_title
        holder.txtPrice.text = "AED ${obj.cart_item_price}"
    }

    inner class CartDetailsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtQuantity: TextView = view.findViewById(R.id.txt_item_quantity)
        val txtTitle: TextView = view.findViewById(R.id.txt_item_title)
        val txtPrice: TextView = view.findViewById(R.id.txt_item_price)
    }
}