package com.squadtechs.markhor.foodapp.trader.activity_trader_edit_company_logo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_pick_location.ActivityPickLocation
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_delivery_range_type.ActivityTraderEditDeliveryRangeType
import com.squadtechs.markhor.foodapp.trader.activity_trader_edit_food_company_details_first_screen.ActivityTraderEditFoodCompanyDetailsFirstScreen
import java.io.ByteArrayOutputStream

class ActivityTraderEditCompanyLogo : AppCompatActivity(), EditLogoContracts.IView {

    private lateinit var imgLogo: ImageView
    private lateinit var imgLocation: ImageView
    private lateinit var imgGoBack: ImageView
    private lateinit var btnNext: Button
    private var coordinates: String? = null
    private var uri: Uri? = null
    private lateinit var bitmap: Bitmap
    private lateinit var mPresenter: EditLogoContracts.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trader_edit_company_logo)
        initViews()
        setListeners()
    }

    private fun setListeners() {

        btnNext.setOnClickListener {
            mPresenter.initValidation(coordinates, uri)
        }

        imgLogo.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), 68)
        }

        imgGoBack.setOnClickListener {
            startActivity(Intent(this, ActivityTraderEditFoodCompanyDetailsFirstScreen::class.java))
            finish()
        }

        imgLocation.setOnClickListener {
            startActivityForResult(
                Intent(
                    this@ActivityTraderEditCompanyLogo,
                    ActivityPickLocation::class.java
                ), 120
            )
        }
    }

    private fun initViews() {
        imgLogo = findViewById(R.id.img_company_logo)
        imgLocation = findViewById(R.id.img_company_address)
        imgGoBack = findViewById(R.id.img_go_back)
        btnNext = findViewById(R.id.btn_trader_edit_company_next)
        mPresenter = EditLogoPresenter(this@ActivityTraderEditCompanyLogo)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 120 && resultCode == Activity.RESULT_OK) {
            coordinates = getSharedPreferences("coordinates", Context.MODE_PRIVATE).getString(
                "coordinates",
                "n/a"
            ) as String
            imgLocation.setImageResource(R.drawable.marker_pin)
        } else if (requestCode == 68 && resultCode == Activity.RESULT_OK) {
            uri = data!!.data!!
            imgLogo.setImageURI(uri!!)
        }
    }

    override fun onValidationResult(status: Boolean) {
        if (status) {
            val imageString = getImageString()
            val pref = getSharedPreferences("logo_string", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("image_string", imageString)
            editor.apply()
            val companyName = intent!!.extras!!.get("company_name") as String
            val companyCuisine = intent!!.extras!!.get("company_cuisine") as String
            val companyPhone = intent!!.extras!!.get("company_phone") as String
            val companyDescription = intent!!.extras!!.get("company_description") as String
            val mIntent = Intent(this, ActivityTraderEditDeliveryRangeType::class.java)
            mIntent.putExtra("company_name", companyName)
            mIntent.putExtra("company_cuisine", companyCuisine)
            mIntent.putExtra("company_phone", companyPhone)
            mIntent.putExtra("company_description", companyDescription)
            //  mIntent.putExtra("company_logo", imageString)
            mIntent.putExtra("company_coordinates", coordinates!!)
            startActivity(mIntent)
            finish()
        } else {
            Toast.makeText(this, "Please fill the form properly", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageString(): String {
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream)
        val byteArr = stream.toByteArray()
        val imageString = Base64.encodeToString(byteArr, Base64.DEFAULT)
        return imageString
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivityTraderEditFoodCompanyDetailsFirstScreen::class.java))
        finish()
    }

}
