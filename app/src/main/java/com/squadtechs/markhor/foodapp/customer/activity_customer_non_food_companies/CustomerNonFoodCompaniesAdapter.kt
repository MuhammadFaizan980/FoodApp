package com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_companies

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.CONSTANTS
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_companies_details.ActivityCustomerNonFoodCompaniesDetails
import com.squareup.picasso.Picasso

class CustomerNonFoodCompaniesAdapter(
    private val list: ArrayList<CustomerNonFoodCompaniesModel>,
    private val context: Context
) : RecyclerView.Adapter<CustomerNonFoodCompaniesAdapter.CustomerNonFoodHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerNonFoodHolder =
        CustomerNonFoodHolder(
            LayoutInflater.from(context).inflate(
                R.layout.trader_non_food_home_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: CustomerNonFoodHolder, position: Int) {
        val obj = list[position]
        Picasso.get().load("${CONSTANTS.imgPre}${obj.company_logo}")
            .into(holder.imgCompany)
        holder.txtTitle.text = obj.company_name
        adjustScreen(holder, position)
        holder.touchView.setOnClickListener {
            val intent = Intent(context, ActivityCustomerNonFoodCompaniesDetails::class.java)
            intent.putExtra("company_id", obj.id)
            intent.putExtra("company_type", obj.company_type)
            context.startActivity(intent)
        }
    }

    private fun adjustScreen(holder: CustomerNonFoodHolder, position: Int) {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        holder.frame.layoutParams = FrameLayout.LayoutParams((width / 2), ((40 * height) / 100))
        if (position % 2 == 0) {
            holder.frame.setPadding(32, 16, 16, 16)
        } else {
            holder.frame.setPadding(16, 16, 32, 16)
        }
    }

    inner class CustomerNonFoodHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCompany: ImageView = view.findViewById(R.id.img_item)
        val txtTitle: TextView = view.findViewById(R.id.txt_item_title)
        val touchView: View = view.findViewById(R.id.touch_view)
        val frame: FrameLayout = view.findViewById(R.id.main_frame)
    }
}