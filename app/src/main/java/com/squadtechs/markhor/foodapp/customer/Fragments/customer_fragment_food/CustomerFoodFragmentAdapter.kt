package com.squadtechs.markhor.foodapp.customer.Fragments.customer_fragment_food

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.squadtechs.markhor.foodapp.CONSTANTS
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.customer_add_to_cart_sheet.CustomerAddToCartSheet
import com.squareup.picasso.Picasso


class CustomerFoodFragmentAdapter(
    private val list: ArrayList<CustomerFoodFragmentModel>,
    private val context: Context,
    private var companyID: String,
    private val manager: FragmentManager
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
        Picasso.get().load("${CONSTANTS.imgPre}${obj.image_path}")
            .into(holder.imgCompany)
        holder.touchView.setOnLongClickListener {

            val pref = context.getSharedPreferences("bc", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(
                "cart_item_url",
                "${CONSTANTS.imgPre}${obj.image_path}"
            )
            editor.putString("cart_item_title", obj.name)
            editor.putString("cart_item_description", obj.description)
            editor.putString("cart_item_price", obj.price)
            editor.putString("cart_item_delivery_price", obj.food_deliveryPrice)
            editor.putString("company_id", companyID)
            editor.putString("item_id", obj.id)
            editor.putString("is_food", "yes")
            editor.putString(
                "customer_id",
                context.getSharedPreferences(
                    "user_credentials",
                    Context.MODE_PRIVATE
                ).getString("id", "none")
            )
            editor.apply()

            val bottomSheet = CustomerAddToCartSheet()
            bottomSheet.show(manager, "lol")
            return@setOnLongClickListener true
        }
    }

    inner class CustomerFoodHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.findViewById(R.id.txt_title)
        val txtDescription: TextView = view.findViewById(R.id.txt_description)
        val txtPrice: TextView = view.findViewById(R.id.txt_price)
        val imgCompany: ImageView = view.findViewById(R.id.img_company)
        val touchView: View = view.findViewById(R.id.touch_view)
    }
}