package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_cart

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R

class CartMainAdapter(private val list: ArrayList<String>, private val context: Context) :
    RecyclerView.Adapter<CartMainAdapter.CartMainHolder>() {
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
        val obj = list[position]
        holder.txtOrder.text = "ITEM COLLECTION # ${position + 1}"
    }

    inner class CartMainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtOrder: TextView = view.findViewById(R.id.txt_item)
    }
}