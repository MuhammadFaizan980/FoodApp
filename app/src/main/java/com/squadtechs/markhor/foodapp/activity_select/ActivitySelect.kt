package com.squadtechs.markhor.foodapp.activity_select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.activity_electronic_license.ActivityElectronicLicense
import com.squadtechs.markhor.foodapp.customer.customer_login.ActivityCustomerLogin
import com.squadtechs.markhor.foodapp.trader.ActivityCompanyType
import com.squadtechs.markhor.foodapp.trader.activity_company_details.ActivityCompanyDetails
import com.squadtechs.markhor.foodapp.trader.activity_company_timings.ActivityCompanyTimings
import com.squadtechs.markhor.foodapp.trader.activity_delivery_details.ActivityDeliveryDetails

class ActivitySelect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
    }

    fun openCustomer(view: View) {
        startActivity(Intent(this, ActivityCustomerLogin::class.java))
        finish()
    }

    fun openTrader(view: View) {
        val isRegInProgress = getSharedPreferences("reg_progress", Context.MODE_PRIVATE).getString(
            "current_screen",
            "null"
        ) as String
        Toast.makeText(this, isRegInProgress, Toast.LENGTH_LONG).show()
        if (!isRegInProgress.equals("null") && !isRegInProgress.equals("reg_complete")) {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Message!")
            dialog.setMessage("Your last registration is still incomplete\nWould you like to continue?")
            dialog.setCancelable(false)
            dialog.setPositiveButton("Continue") { dialogInterface, i ->
                when (isRegInProgress) {
                    "trader company details" -> {
                        startActivity(Intent(this, ActivityCompanyDetails::class.java))
                        finish()
                    }
                    "trader company timings" -> {
                        startActivity(Intent(this, ActivityCompanyTimings::class.java))
                        finish()
                    }
                    "trader delivery details" -> {
                        startActivity(Intent(this, ActivityDeliveryDetails::class.java))
                        finish()
                    }
                    "trader license" -> {
                        startActivity(Intent(this, ActivityElectronicLicense::class.java))
                        finish()
                    }
                }
            }
                .setNegativeButton("Cancel") { dialogInterface, i ->
                    startActivity(Intent(this@ActivitySelect, ActivityCompanyType::class.java))
                    finish()
                }
            dialog.show()
        } else {
            startActivity(Intent(this@ActivitySelect, ActivityCompanyType::class.java))
            finish()
        }
    }

}
