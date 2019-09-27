package com.squadtechs.markhor.foodapp.trader.activity_trader_order_details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R


class TraderOrderDetailsAdapter(
    private val list: ArrayList<TraderOrderDetailsModel>,
    private val context: Context
) : RecyclerView.Adapter<TraderOrderDetailsAdapter.CustomerOrderDetailsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerOrderDetailsHolder =
        CustomerOrderDetailsHolder(
            LayoutInflater.from(context).inflate(
                R.layout.cart_details_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: CustomerOrderDetailsHolder, position: Int) {
        val obj = list[position]
        holder.txtQuantity.text = "x${obj.quantity}"
        holder.txtItemPrice.text = "AED ${obj.itemPrice}"
        holder.txtItemTitle.text = obj.titleofItem
    }

    inner class CustomerOrderDetailsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtQuantity: TextView = view.findViewById(R.id.txt_item_quantity)
        val txtItemTitle: TextView = view.findViewById(R.id.txt_item_title)
        val txtItemPrice: TextView = view.findViewById(R.id.txt_item_price)
    }
}