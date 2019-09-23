package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_non_food

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squareup.picasso.Picasso

class CustomerFragmentNonFoodAdapter(
    private val list: ArrayList<CustomerFragmentNonFoodModel>,
    private val context: Context
) : RecyclerView.Adapter<CustomerFragmentNonFoodAdapter.CustomerNonFoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerNonFoodViewHolder =
        CustomerNonFoodViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.trader_non_food_home_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: CustomerNonFoodViewHolder, position: Int) {
        val obj = list[position]
        Picasso.get().load("http://squadtechsolution.com/android/v1/${obj.image_path}")
            .into(holder.imgItem)
        holder.txtTitle.text = obj.name
        adjustScreen(holder, position)
    }

    private fun adjustScreen(holder: CustomerNonFoodViewHolder, position: Int) {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        holder.frame.layoutParams = FrameLayout.LayoutParams((width / 2), ((27 * height) / 100))
        if (position % 2 == 0) {
            holder.frame.setPadding(64, 32, 32, 32)
        } else {
            holder.frame.setPadding(32, 32, 64, 21)
        }
    }

    inner class CustomerNonFoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgItem: ImageView = view.findViewById(R.id.img_item)
        val frame: FrameLayout = view.findViewById(R.id.main_frame)
        val touchView: View = view.findViewById(R.id.touch_view)
        val txtTitle: TextView = view.findViewById(R.id.txt_item_title)
    }
}