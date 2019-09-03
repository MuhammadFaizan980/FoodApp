package com.squadtechs.markhor.foodapp.trader.activity_company_details

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.squadtechs.markhor.foodapp.R

class ActivityCompanyDetails : AppCompatActivity(), TraderCompanyDetailsContracts.IView {

    private lateinit var edtName: EditText
    private lateinit var edtCuisine: EditText
    private lateinit var edtDescrption: EditText
    private lateinit var imgCompany: ImageView
    private lateinit var btnNext: Button
    private lateinit var linearback: LinearLayout
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_details)
        initViews()
    }

    private fun initViews() {
        edtName = findViewById(R.id.edt_company_name)
        edtCuisine = findViewById(R.id.edt_cuisine_type)
        edtDescrption = findViewById(R.id.edt_company_description)
        imgCompany = findViewById(R.id.img_company_photo)
        btnNext = findViewById(R.id.btn_trader_next)
        linearback = findViewById(R.id.linear_go_back)
    }

    override fun onValidationResult(valid: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAddCompanyDetailsResult(status: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
