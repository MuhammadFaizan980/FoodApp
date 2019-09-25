package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_cart

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_cutomer_cart_items_details.ActivityCustomerCartItemsDetails
import com.squadtechs.markhor.foodapp.customer.db_utiils.DbUtils

class CartMainAdapter(
    private val list: ArrayList<String>, private val context: Context,
    private val mView: CartCallBack
) :
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
        holder.txtOrder.text = "COLLECTION # ${position + 1}"
        holder.txtOrder.setOnClickListener {
            context.startActivity(
                Intent(
                    context,
                    ActivityCustomerCartItemsDetails::class.java
                ).putExtra("company_id", obj)
            )
        }

        holder.txtOrder.setOnLongClickListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Message!")
            dialog.setMessage("Are you sure you want to remove this item collection from cart?")
            dialog.setPositiveButton("Delete") { dialogInterface, i ->
                val db = DbUtils(context)
                if (db.deleteCollection(obj)) {
                    list.remove(obj)
                    notifyItemChanged(position)
                    notifyItemRangeChanged(position, list.size)
                    notifyDataSetChanged()
                    mView.itemDeleted()
                } else {
                    Toast.makeText(context, "Cannot delete", Toast.LENGTH_SHORT).show()
                }

            }
                .setNegativeButton("Camcel") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
            dialog.show()
            return@setOnLongClickListener true
        }

    }

    inner class CartMainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtOrder: TextView = view.findViewById(R.id.txt_item)
    }
}