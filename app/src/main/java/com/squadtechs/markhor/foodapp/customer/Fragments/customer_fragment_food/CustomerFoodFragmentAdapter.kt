package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_food

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_food.FragmentFoodCallback
import com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_food.TraderFoodModel
import com.squareup.picasso.Picasso


class CustomerFoodFragmentAdapter(
    private val list: ArrayList<CustomerFoodFragmentModel>,
    private val context: Context
) : RecyclerView.Adapter<CustomerFoodFragmentAdapter.CustomerFoodHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerFoodHolder =
        CustomerFoodHolder(
            LayoutInflater.from(context).inflate(
                R.layout.customer_company_details_food_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: CustomerFoodHolder, position: Int) {
        val obj = list[position]
        holder.txtTitle.text = obj.name
        holder.txtDescription.text = obj.description
        holder.txtPrice.text = obj.price
        Picasso.get().load("http://squadtechsolution.com/android/v1/${obj.image_path}")
            .into(holder.imgCompany)
    }

    inner class CustomerFoodHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.findViewById(R.id.txt_title)
        val txtDescription: TextView = view.findViewById(R.id.txt_description)
        val txtPrice: TextView = view.findViewById(R.id.txt_price)
        val imgCompany: ImageView = view.findViewById(R.id.img_company)
        val touchView: View = view.findViewById(R.id.touch_view)
    }
}