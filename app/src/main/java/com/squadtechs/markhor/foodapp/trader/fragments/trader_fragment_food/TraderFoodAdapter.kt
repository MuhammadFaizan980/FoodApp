package com.squadtechs.markhor.foodapp.trader.fragments.trader_fragment_food

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.R
import com.squareup.picasso.Picasso

class TraderFoodAdapter(
    private val list: ArrayList<TraderFoodModel>,
    private val context: Context,
    private val mView: FragmentFoodCallback
) : RecyclerView.Adapter<TraderFoodAdapter.TraderFoodHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraderFoodHolder =
        TraderFoodHolder(
            LayoutInflater.from(context).inflate(
                R.layout.trader_food_row_design,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: TraderFoodHolder, position: Int) {
        val obj = list[position]
        holder.txtTitle.text = obj.name
        holder.txtDescription.text = obj.description
        holder.txtPrice.text = obj.price
        Picasso.get().load("http://squadtechsolution.com/android/v1/${obj.image_path}")
            .into(holder.imgCompany)
        holder.touchFrame.setOnClickListener {
            val pref = context.getSharedPreferences("add_item_preferences", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putBoolean("is_edit", true)
            editor.putString("food_id", obj.id)
            editor.apply()
            mView.onEditClicked()
        }
    }

    inner class TraderFoodHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.findViewById(R.id.txt_title)
        val txtDescription: TextView = view.findViewById(R.id.txt_description)
        val txtPrice: TextView = view.findViewById(R.id.txt_price)
        val imgCompany: ImageView = view.findViewById(R.id.img_company)
        val touchFrame: FrameLayout = view.findViewById(R.id.touch_frame)
    }
}