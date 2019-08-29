package com.squadtechs.markhor.foodapp.trader

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.activity_select.ActivitySelect

class ActivityCompanyType : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_type)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, ActivitySelect::class.java))
        finish()
    }

}
