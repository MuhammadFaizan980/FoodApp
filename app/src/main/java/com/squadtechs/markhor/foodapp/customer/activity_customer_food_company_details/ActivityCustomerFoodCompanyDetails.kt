package com.squadtechs.markhor.foodapp.customer.activity_customer_food_company_details

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.customer.activity_customer_to_trader_chat.ActivityCustomerToTraderChat
import com.squadtechs.markhor.foodapp.customer.customer_util.CustomerUtils
import com.squareup.picasso.Picasso
import java.math.RoundingMode
import java.text.DecimalFormat

class ActivityCustomerFoodCompanyDetails : AppCompatActivity(),
    CustomerFoodCompanyDetailsContracts.IView {

    private lateinit var viewPackage: ViewPager
    private lateinit var txtAll: TextView
    private lateinit var txtStarters: TextView
    private lateinit var txtMain: TextView
    private lateinit var txtSides: TextView
    private lateinit var txtDeserts: TextView
    private lateinit var txtTitle: TextView
    private lateinit var txtTime: TextView
    private lateinit var txtDeliveryType: TextView
    private lateinit var txtDescription: TextView
    //  private lateinit var txtNewMessage: TextView
    private lateinit var imgCompany: ImageView
    private lateinit var imgChat: ImageView
    private lateinit var imgBack: ImageView
    private lateinit var mPresenter: CustomerFoodCompanyDetailsContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_food_company_details)
        initViews()
        setListeners()
        mPresenter.getCompanyData(intent!!.extras!!.get("company_id") as String)
    }

    private fun setListeners() {
        viewPackage.adapter =
            CompanyInformationPagerAdapter(
                supportFragmentManager,
                intent!!.extras!!.get("company_id") as String
            )
        viewPackage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_selected)
                        txtStarters.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMain.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtSides.setBackgroundResource(R.drawable.tab_back_unselected)
                    }

                    1 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtStarters.setBackgroundResource(R.drawable.tab_back_selected)
                        txtMain.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtSides.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtDeserts.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    2 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtStarters.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMain.setBackgroundResource(R.drawable.tab_back_selected)
                        txtSides.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtDeserts.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    3 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtStarters.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMain.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtSides.setBackgroundResource(R.drawable.tab_back_selected)
                        txtDeserts.setBackgroundResource(R.drawable.tab_back_unselected)
                    }
                    4 -> {
                        txtAll.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtStarters.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtMain.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtSides.setBackgroundResource(R.drawable.tab_back_unselected)
                        txtDeserts.setBackgroundResource(R.drawable.tab_back_selected)
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

    private fun takeUserToChatScreen() {
        val mIntent = Intent(this, ActivityCustomerToTraderChat::class.java)
        mIntent.putExtra("company_id", intent!!.extras!!.get("company_id") as String)
        startActivity(mIntent)

    }

    private fun initViews() {
        imgChat = findViewById(R.id.img_chat)
        viewPackage = findViewById(R.id.company_view_pager)
        txtAll = findViewById(R.id.txt_all)
        txtStarters = findViewById(R.id.txt_starters)
        txtMain = findViewById(R.id.txt_main_course)
        txtSides = findViewById(R.id.txt_sides)
        txtDeserts = findViewById(R.id.txt_deserts)
        txtTitle = findViewById(R.id.txt_title)
        txtDescription = findViewById(R.id.txt_description)
        txtTime = findViewById(R.id.txt_time)
        txtDeliveryType = findViewById(R.id.txt_delivery_type)
        imgCompany = findViewById(R.id.img_company_image)
        imgBack = findViewById(R.id.img_go_back)
        mPresenter = CustomerFoodCompanyDetailsPresenter(
            this@ActivityCustomerFoodCompanyDetails,
            this
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onGetCompanyDataResult(
        error: Boolean,
        responseObj: CustomerFoodCompanyDetailsResponseHandler?
    ) {
        if (!error) {
            Picasso.get()
                .load("http://squadtechsolution.com/android/v1/${responseObj?.company_logo}")
                .into(imgCompany)
            txtTitle.text = responseObj?.company_name
            txtDescription.text = responseObj?.company_description
            txtDeliveryType.text = "Delivery Type: ${responseObj?.delivery_type}"
            val companyLatLng = CustomerUtils.decodeCoordinates(responseObj?.address as String)
            txtTime.text = responseObj?.delivery_timing
        } else {
            Toast.makeText(this, "There was an error", Toast.LENGTH_LONG).show()
        }
    }

    private fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }

}
