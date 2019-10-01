package com.squadtechs.markhor.foodapp.customer.activity_customer_non_food_companies_details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squadtechs.markhor.foodapp.CONSTANTS
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_to_trader_chat.ActivityCustomerToTraderChat
import com.squareup.picasso.Picasso
import org.json.JSONArray

class ActivityCustomerNonFoodCompaniesDetails : AppCompatActivity() {
    private lateinit var viewPackage: ViewPager

    //tabs layouts
    private lateinit var clothesTabsLayout: LinearLayout
    private lateinit var accessoriesTabsLayout: LinearLayout
    private lateinit var skincareTabsLayout: LinearLayout
    private lateinit var homewareTabsLayout: LinearLayout
    private lateinit var toysTabsLayout: LinearLayout
    private lateinit var shoesTabsLayout: LinearLayout
    private lateinit var bagsTabsLayout: LinearLayout
    private lateinit var otherTabsLayout: LinearLayout

    //-----END-----\\

    //Clothes tabs textviews
    private lateinit var txtAllClothes: TextView
    private lateinit var txtWomenClothes: TextView
    private lateinit var txtMenClothes: TextView
    private lateinit var txtChildrenClothes: TextView

    //Accessories tabs textviews
    private lateinit var txtAllAccessories: TextView
    private lateinit var txtWomenAccessories: TextView
    private lateinit var txtMenAccessories: TextView
    private lateinit var txtChildrenAccessories: TextView
    private lateinit var txtUnisexAccessories: TextView
    private lateinit var txtOtherAccessories: TextView

    //Skincare tabs textviews
    private lateinit var txtAllSkincare: TextView
    private lateinit var txtFaceAndBodySkincare: TextView
    private lateinit var txtNailsSkincare: TextView
    private lateinit var txtOtherSkincare: TextView

    //Homeware tabs textviews
    private lateinit var txtAllHomeware: TextView
    private lateinit var txtBedroomHomeware: TextView
    private lateinit var txtLivingRoomHomeware: TextView
    private lateinit var txtPatioHomeware: TextView
    private lateinit var txtOtherHomeware: TextView

    //Toys tabs textviews
    private lateinit var txtAllToys: TextView
    private lateinit var txtBoysToys: TextView
    private lateinit var txtGirlsToys: TextView
    private lateinit var txtAdultsToys: TextView
    private lateinit var txtUnisexToys: TextView
    private lateinit var txtOtherToys: TextView

    //Shoes tabs textviews
    private lateinit var txtAllShoes: TextView
    private lateinit var txtMenShoes: TextView
    private lateinit var txtWomenShoes: TextView
    private lateinit var txtChildrenShoes: TextView
    private lateinit var txtUnisexShoes: TextView

    //Bags tabs textviews
    private lateinit var txtAllBags: TextView
    private lateinit var txtHandBagsBags: TextView
    private lateinit var txtOtherBags: TextView

    //Others tabs textviews
    private lateinit var txtOtherOthers: TextView

    private lateinit var txtTitle: TextView
    private lateinit var txtTime: TextView
    private lateinit var txtDeliveryType: TextView
    private lateinit var txtDescription: TextView
    private lateinit var imgCompany: ImageView
    private lateinit var imgChat: ImageView
    private lateinit var imgBack: ImageView
    private lateinit var adapter: ClothesPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_non_food_companies_details)
        initViews()
        setListeners()
        hideShowtabs()
        setClothesTabListeners()
        setAccessoriesTabListener()
        setSkincareTabListener()
        setHomewareTabListener()
        setToysTabListener()
        setShoesTabListener()
        setBagsTabListener()
    }

    private fun setBagsTabListener() {
        txtAllBags.setOnClickListener {
            txtAllBags.setBackgroundResource(R.drawable.tab_back_selected)
            txtHandBagsBags.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherBags.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(0)
        }
        txtHandBagsBags.setOnClickListener {
            txtAllBags.setBackgroundResource(R.drawable.tab_back_unselected)
            txtHandBagsBags.setBackgroundResource(R.drawable.tab_back_selected)
            txtOtherBags.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(1)
        }
        txtOtherBags.setOnClickListener {
            txtAllBags.setBackgroundResource(R.drawable.tab_back_unselected)
            txtHandBagsBags.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherBags.setBackgroundResource(R.drawable.tab_back_selected)
            viewPackage.setCurrentItem(2)
        }
    }

    private fun setShoesTabListener() {
        txtAllShoes.setOnClickListener {
            txtAllShoes.setBackgroundResource(R.drawable.tab_back_selected)
            txtMenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(0)
        }
        txtMenShoes.setOnClickListener {
            txtAllShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenShoes.setBackgroundResource(R.drawable.tab_back_selected)
            txtWomenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(1)
        }
        txtWomenShoes.setOnClickListener {
            txtAllShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenShoes.setBackgroundResource(R.drawable.tab_back_selected)
            txtChildrenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(2)
        }
        txtChildrenShoes.setOnClickListener {
            txtAllShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenShoes.setBackgroundResource(R.drawable.tab_back_selected)
            txtUnisexShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(3)
        }
        txtUnisexShoes.setOnClickListener {
            txtAllShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexShoes.setBackgroundResource(R.drawable.tab_back_selected)
            viewPackage.setCurrentItem(4)
        }
    }

    private fun setToysTabListener() {
        txtAllToys.setOnClickListener {
            txtAllToys.setBackgroundResource(R.drawable.tab_back_selected)
            txtBoysToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtGirlsToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtAdultsToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherToys.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(0)
        }
        txtBoysToys.setOnClickListener {
            txtAllToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtBoysToys.setBackgroundResource(R.drawable.tab_back_selected)
            txtGirlsToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtAdultsToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherToys.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(1)
        }
        txtGirlsToys.setOnClickListener {
            txtAllToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtBoysToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtGirlsToys.setBackgroundResource(R.drawable.tab_back_selected)
            txtUnisexToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtAdultsToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherToys.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(2)
        }
        txtUnisexToys.setOnClickListener {
            txtAllToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtBoysToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtGirlsToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexToys.setBackgroundResource(R.drawable.tab_back_selected)
            txtAdultsToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherToys.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(3)
        }
        txtAdultsToys.setOnClickListener {
            txtAllToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtBoysToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtGirlsToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtAdultsToys.setBackgroundResource(R.drawable.tab_back_selected)
            txtOtherToys.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(4)
        }
        txtOtherToys.setOnClickListener {
            txtAllToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtBoysToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtGirlsToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtAdultsToys.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherToys.setBackgroundResource(R.drawable.tab_back_selected)
            viewPackage.setCurrentItem(5)
        }
    }

    private fun setHomewareTabListener() {
        txtAllHomeware.setOnClickListener {
            txtAllHomeware.setBackgroundResource(R.drawable.tab_back_selected)
            txtBedroomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtLivingRoomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtPatioHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(0)
        }
        txtBedroomHomeware.setOnClickListener {
            txtAllHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtBedroomHomeware.setBackgroundResource(R.drawable.tab_back_selected)
            txtLivingRoomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtPatioHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(1)
        }
        txtLivingRoomHomeware.setOnClickListener {
            txtAllHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtBedroomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtLivingRoomHomeware.setBackgroundResource(R.drawable.tab_back_selected)
            txtPatioHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(2)
        }
        txtPatioHomeware.setOnClickListener {
            txtAllHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtBedroomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtLivingRoomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtPatioHomeware.setBackgroundResource(R.drawable.tab_back_selected)
            txtOtherHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(3)
        }
        txtOtherHomeware.setOnClickListener {
            txtAllHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtBedroomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtLivingRoomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtPatioHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherHomeware.setBackgroundResource(R.drawable.tab_back_selected)
            viewPackage.setCurrentItem(4)
        }
    }

    private fun setSkincareTabListener() {
        txtAllSkincare.setOnClickListener {
            txtAllSkincare.setBackgroundResource(R.drawable.tab_back_selected)
            txtFaceAndBodySkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            txtNailsSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(0)
        }
        txtFaceAndBodySkincare.setOnClickListener {
            txtAllSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            txtFaceAndBodySkincare.setBackgroundResource(R.drawable.tab_back_selected)
            txtNailsSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(1)
        }
        txtNailsSkincare.setOnClickListener {
            txtAllSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            txtFaceAndBodySkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            txtNailsSkincare.setBackgroundResource(R.drawable.tab_back_selected)
            txtOtherSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(2)
        }
        txtOtherSkincare.setOnClickListener {
            txtAllSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            txtFaceAndBodySkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            txtNailsSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherSkincare.setBackgroundResource(R.drawable.tab_back_selected)
            viewPackage.setCurrentItem(3)
        }
    }

    private fun setAccessoriesTabListener() {
        txtAllAccessories.setOnClickListener {
            txtAllAccessories.setBackgroundResource(R.drawable.tab_back_selected)
            txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(0)
        }
        txtWomenAccessories.setOnClickListener {
            txtAllAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_selected)
            txtMenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(1)
        }
        txtMenAccessories.setOnClickListener {
            txtAllAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenAccessories.setBackgroundResource(R.drawable.tab_back_selected)
            txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(2)
        }
        txtChildrenAccessories.setOnClickListener {
            txtAllAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_selected)
            txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(3)
        }
        txtUnisexAccessories.setOnClickListener {
            txtAllAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_selected)
            txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(4)
        }
        txtOtherAccessories.setOnClickListener {
            txtAllAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
            txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_selected)
            viewPackage.setCurrentItem(5)
        }
    }

    private fun setClothesTabListeners() {
        txtAllClothes.setOnClickListener {
            txtAllClothes.setBackgroundResource(R.drawable.tab_back_selected)
            txtWomenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(0)
        }
        txtWomenClothes.setOnClickListener {
            txtAllClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenClothes.setBackgroundResource(R.drawable.tab_back_selected)
            txtMenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(1)
        }
        txtMenClothes.setOnClickListener {
            txtAllClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenClothes.setBackgroundResource(R.drawable.tab_back_selected)
            txtChildrenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            viewPackage.setCurrentItem(2)
        }
        txtChildrenClothes.setOnClickListener {
            txtAllClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtWomenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtMenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
            txtChildrenClothes.setBackgroundResource(R.drawable.tab_back_selected)
            viewPackage.setCurrentItem(3)
        }
    }

    private fun hideShowtabs() {
        when (intent!!.extras!!.get("company_type") as String) {
            "Clothes" -> {
                clothesTabsLayout.visibility = View.VISIBLE
            }
            "Accessories" -> {
                accessoriesTabsLayout.visibility = View.VISIBLE
            }
            "Skincare" -> {
                skincareTabsLayout.visibility = View.VISIBLE
            }
            "Homeware" -> {
                homewareTabsLayout.visibility = View.VISIBLE
            }
            "Toys" -> {
                toysTabsLayout.visibility = View.VISIBLE
            }
            "Shoes" -> {
                shoesTabsLayout.visibility = View.VISIBLE
            }
            "Bags" -> {
                bagsTabsLayout.visibility = View.VISIBLE
            }
            "Other" -> {
                otherTabsLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun setListeners() {

        when (intent!!.extras!!.get("company_type") as String) {
            "Clothes" -> {
                adapter = ClothesPagerAdapter(
                    intent!!.extras!!.get("company_id") as String,
                    "Clothes", 4, supportFragmentManager
                )
            }
            "Accessories" -> {
                adapter = ClothesPagerAdapter(
                    intent!!.extras!!.get("company_id") as String,
                    "Accessories", 6, supportFragmentManager
                )
            }
            "Skincare" -> {
                adapter = ClothesPagerAdapter(
                    intent!!.extras!!.get("company_id") as String,
                    "Skincare", 4, supportFragmentManager
                )
            }
            "Homeware" -> {
                adapter = ClothesPagerAdapter(
                    intent!!.extras!!.get("company_id") as String,
                    "Homeware", 5, supportFragmentManager
                )
            }
            "Toys" -> {
                adapter = ClothesPagerAdapter(
                    intent!!.extras!!.get("company_id") as String,
                    "Toys", 6, supportFragmentManager
                )
            }
            "Shoes" -> {
                adapter = ClothesPagerAdapter(
                    intent!!.extras!!.get("company_id") as String,
                    "Shoes", 5, supportFragmentManager
                )
            }
            "Bags" -> {
                adapter = ClothesPagerAdapter(
                    intent!!.extras!!.get("company_id") as String,
                    "Bags", 3, supportFragmentManager
                )
            }
            "Other" -> {
                adapter = ClothesPagerAdapter(
                    intent!!.extras!!.get("company_id") as String,
                    "Other", 1, supportFragmentManager
                )
            }
        }

        viewPackage.adapter = adapter
        viewPackage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                when (intent!!.extras!!.get("company_type") as String) {
                    "Clothes" -> {
                        when (position) {
                            0 -> {
                                txtAllClothes.setBackgroundResource(R.drawable.tab_back_selected)
                                txtWomenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                            }

                            1 -> {
                                txtAllClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenClothes.setBackgroundResource(R.drawable.tab_back_selected)
                                txtMenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            2 -> {
                                txtAllClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenClothes.setBackgroundResource(R.drawable.tab_back_selected)
                                txtChildrenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            3 -> {
                                txtAllClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenClothes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenClothes.setBackgroundResource(R.drawable.tab_back_selected)
                            }
                        }
                    }
                    "Accessories" -> {
                        when (position) {
                            0 -> {
                                txtAllAccessories.setBackgroundResource(R.drawable.tab_back_selected)
                                txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                            }

                            1 -> {
                                txtAllAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_selected)
                                txtMenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            2 -> {
                                txtAllAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenAccessories.setBackgroundResource(R.drawable.tab_back_selected)
                                txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            3 -> {
                                txtAllAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_selected)
                                txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            4 -> {
                                txtAllAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_selected)
                                txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            5 -> {
                                txtAllAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexAccessories.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherAccessories.setBackgroundResource(R.drawable.tab_back_selected)
                            }
                        }
                    }
                    "Skincare" -> {
                        when (position) {
                            0 -> {
                                txtAllSkincare.setBackgroundResource(R.drawable.tab_back_selected)
                                txtFaceAndBodySkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtNailsSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                            }

                            1 -> {
                                txtAllSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtFaceAndBodySkincare.setBackgroundResource(R.drawable.tab_back_selected)
                                txtNailsSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            2 -> {
                                txtAllSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtFaceAndBodySkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtNailsSkincare.setBackgroundResource(R.drawable.tab_back_selected)
                                txtOtherSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            3 -> {
                                txtAllSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtFaceAndBodySkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtNailsSkincare.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherSkincare.setBackgroundResource(R.drawable.tab_back_selected)
                            }
                        }
                    }
                    "Homeware" -> {
                        when (position) {
                            0 -> {
                                txtAllHomeware.setBackgroundResource(R.drawable.tab_back_selected)
                                txtBedroomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtLivingRoomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtPatioHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                            }

                            1 -> {
                                txtAllHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtBedroomHomeware.setBackgroundResource(R.drawable.tab_back_selected)
                                txtLivingRoomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtPatioHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            2 -> {
                                txtAllHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtBedroomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtLivingRoomHomeware.setBackgroundResource(R.drawable.tab_back_selected)
                                txtPatioHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            3 -> {
                                txtAllHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtBedroomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtLivingRoomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtPatioHomeware.setBackgroundResource(R.drawable.tab_back_selected)
                                txtOtherHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            4 -> {
                                txtAllHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtBedroomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtLivingRoomHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtPatioHomeware.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherHomeware.setBackgroundResource(R.drawable.tab_back_selected)
                            }
                        }
                    }
                    "Toys" -> {
                        when (position) {
                            0 -> {
                                txtAllToys.setBackgroundResource(R.drawable.tab_back_selected)
                                txtBoysToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtGirlsToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtAdultsToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherToys.setBackgroundResource(R.drawable.tab_back_unselected)
                            }

                            1 -> {
                                txtAllToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtBoysToys.setBackgroundResource(R.drawable.tab_back_selected)
                                txtGirlsToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtAdultsToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherToys.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            2 -> {
                                txtAllToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtBoysToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtGirlsToys.setBackgroundResource(R.drawable.tab_back_selected)
                                txtUnisexToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtAdultsToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherToys.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            3 -> {
                                txtAllToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtBoysToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtGirlsToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexToys.setBackgroundResource(R.drawable.tab_back_selected)
                                txtAdultsToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherToys.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            4 -> {
                                txtAllToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtBoysToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtGirlsToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtAdultsToys.setBackgroundResource(R.drawable.tab_back_selected)
                                txtOtherToys.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            5 -> {
                                txtAllToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtBoysToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtGirlsToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtAdultsToys.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherToys.setBackgroundResource(R.drawable.tab_back_selected)
                            }
                        }
                    }
                    "Shoes" -> {
                        when (position) {
                            0 -> {
                                txtAllShoes.setBackgroundResource(R.drawable.tab_back_selected)
                                txtMenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                            }

                            1 -> {
                                txtAllShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenShoes.setBackgroundResource(R.drawable.tab_back_selected)
                                txtWomenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            2 -> {
                                txtAllShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenShoes.setBackgroundResource(R.drawable.tab_back_selected)
                                txtChildrenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            3 -> {
                                txtAllShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenShoes.setBackgroundResource(R.drawable.tab_back_selected)
                                txtUnisexShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            4 -> {
                                txtAllShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtMenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtWomenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtChildrenShoes.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtUnisexShoes.setBackgroundResource(R.drawable.tab_back_selected)
                            }
                        }
                    }
                    "Bags" -> {
                        when (position) {
                            0 -> {
                                txtAllBags.setBackgroundResource(R.drawable.tab_back_selected)
                                txtHandBagsBags.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherBags.setBackgroundResource(R.drawable.tab_back_unselected)
                            }

                            1 -> {
                                txtAllBags.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtHandBagsBags.setBackgroundResource(R.drawable.tab_back_selected)
                                txtOtherBags.setBackgroundResource(R.drawable.tab_back_unselected)
                            }
                            2 -> {
                                txtAllBags.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtHandBagsBags.setBackgroundResource(R.drawable.tab_back_unselected)
                                txtOtherBags.setBackgroundResource(R.drawable.tab_back_selected)
                            }
                        }
                    }
                }
            }

        })

        imgBack.setOnClickListener {
            finish()
        }

        imgChat.setOnClickListener {
            takeUserToChatScreen()
        }


    }

    private fun initViews() {
        imgChat = findViewById(R.id.img_chat)
        viewPackage = findViewById(R.id.clothes_view_pager)

        //tabs layouts initialization
        clothesTabsLayout = findViewById(R.id.clothes_tabs)
        accessoriesTabsLayout = findViewById(R.id.accessories_tabs)
        skincareTabsLayout = findViewById(R.id.skincare_tabs)
        homewareTabsLayout = findViewById(R.id.homeware_tabs)
        toysTabsLayout = findViewById(R.id.toys_tabs)
        shoesTabsLayout = findViewById(R.id.shoes_tabs)
        bagsTabsLayout = findViewById(R.id.bags_tabs)
        otherTabsLayout = findViewById(R.id.other_tabs)

        //Clothes tabs initialization
        txtAllClothes = findViewById(R.id.txt_all_clothes)
        txtWomenClothes = findViewById(R.id.txt_women_clothes)
        txtMenClothes = findViewById(R.id.txt_men_clothes)
        txtChildrenClothes = findViewById(R.id.txt_children_clothes)

        //Accessories tabs initialization
        txtAllAccessories = findViewById(R.id.txt_all_accessories)
        txtMenAccessories = findViewById(R.id.txt_men_accessories)
        txtWomenAccessories = findViewById(R.id.txt_women_accessories)
        txtChildrenAccessories = findViewById(R.id.txt_children_accessories)
        txtUnisexAccessories = findViewById(R.id.txt_unisex_accessories)
        txtOtherAccessories = findViewById(R.id.txt_other_accessories)

        //Skinacare tabs initialization
        txtAllSkincare = findViewById(R.id.txt_all_skincare)
        txtFaceAndBodySkincare = findViewById(R.id.txt_face_and_body_skincare)
        txtNailsSkincare = findViewById(R.id.txt_nails_skincare)
        txtOtherSkincare = findViewById(R.id.txt_other_skincare)

        //Homeware tabs initialization
        txtAllHomeware = findViewById(R.id.txt_all_homeware)
        txtBedroomHomeware = findViewById(R.id.txt_bedroom_homeware)
        txtLivingRoomHomeware = findViewById(R.id.txt_living_room_homeware)
        txtPatioHomeware = findViewById(R.id.txt_patio_homeware)
        txtOtherHomeware = findViewById(R.id.txt_other_homeware)

        //Toys tabs initialization
        txtAllToys = findViewById(R.id.txt_all_toys)
        txtBoysToys = findViewById(R.id.txt_boys_toys)
        txtGirlsToys = findViewById(R.id.txt_girls_toys)
        txtAdultsToys = findViewById(R.id.txt_adults_toys)
        txtUnisexToys = findViewById(R.id.txt_unisex_toys)
        txtOtherToys = findViewById(R.id.txt_other_toys)

        //Shoes tabs initialization
        txtAllShoes = findViewById(R.id.txt_all_shoes)
        txtMenShoes = findViewById(R.id.txt_men_shoes)
        txtWomenShoes = findViewById(R.id.txt_women_shoes)
        txtChildrenShoes = findViewById(R.id.txt_children_shoes)
        txtUnisexShoes = findViewById(R.id.txt_unisex_shoes)

        //Bags tabs initialization
        txtAllBags = findViewById(R.id.txt_all_bags)
        txtHandBagsBags = findViewById(R.id.txt_hand_bags_bags)
        txtOtherBags = findViewById(R.id.txt_other_bags)

        //Others tabs initialization
        txtOtherOthers = findViewById(R.id.txt_other_others)


        txtTitle = findViewById(R.id.txt_title)
        txtDescription = findViewById(R.id.txt_description)
        txtTime = findViewById(R.id.txt_time)
        txtDeliveryType = findViewById(R.id.txt_delivery_type)
        imgCompany = findViewById(R.id.img_company_image)
        imgBack = findViewById(R.id.img_go_back)
        fetchSingleCompanyData()
    }

    private fun takeUserToChatScreen() {
        val mIntent = Intent(this, ActivityCustomerToTraderChat::class.java)
        mIntent.putExtra("company_id", intent!!.extras!!.get("company_id") as String)
        startActivity(mIntent)
    }

    private fun fetchSingleCompanyData() {
        val API = "http://squadtechsolution.com/android/v1/allcompany.php"
        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, API,
            Response.Listener { response ->
                Log.i("dxdiag", response)
                try {

                    val jsonArr = JSONArray(response)
                    var count = (jsonArr.length() - 1)
                    while (count >= 0) {
                        val json = jsonArr.getJSONObject(count)
                        if (json.getString("id").equals(intent!!.extras!!.get("company_id") as String)
                        ) {
                            Picasso.get()
                                .load("${CONSTANTS.imgPre}${json.getString("company_logo")}")
                                .into(imgCompany)
                            txtTitle.text = json.getString("company_name")
                            txtDeliveryType.text = "Delivery: ${json.getString("delivery_type")}"
                            txtTime.text = json.getString("delivery_timing")
                            txtDescription.text = json.getString("company_description")
                            break
                        }
                        count--
                    }

                } catch (exc: Exception) {
                    Log.i("dxdiag", exc.toString())
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            })
        requestQueue.add(stringRequest)
    }
}
