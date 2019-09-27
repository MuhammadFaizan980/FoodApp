package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_orders

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_trader_order_details.ActivityTraderOrderDetails

class TraderOrderMainAdapter(
    private val list: ArrayList<TraderOrderMainModel>, private val context: Context,
    private val mPosition: Int
) :
    RecyclerView.Adapter<TraderOrderMainAdapter.CartMainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartMainHolder =
        CartMainHolder(
            LayoutInflater.from(context).inflate(
                R.layout.cart_main_row_design, parent, false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartMainHolder, position: Int) {
        holder.txtOrder.text = "ORDER # ${position + 1}"
        holder.txtOrder.setOnClickListener {
            (context as Activity).startActivityForResult(
                Intent(
                    context,
                    ActivityTraderOrderDetails::class.java
                ).putExtra("position", mPosition)
                    .putExtra("customer_id", list[position].customer), 77
            )
        }
    }

    inner class CartMainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtOrder: TextView = view.findViewById(R.id.txt_item)
    }
}