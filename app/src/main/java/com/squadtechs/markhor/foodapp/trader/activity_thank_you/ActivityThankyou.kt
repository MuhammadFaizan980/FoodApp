package com.squadtechs.markhor.foodapp.trader.activity_thank_you

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.squadtechs.markhor.foodapp.R
import com.squadtechs.markhor.foodapp.trader.trader_login.ActivityTraderLogin

class ActivityThankyou : AppCompatActivity() {
    private lateinit var btnDone: Button
    private lateinit var linearAlreadyRegistered: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thankyou)
        initViews()
        setListenres()
    }

    private fun setListenres() {
        btnDone.setOnClickListener {
            startActivity(Intent(this@ActivityThankyou, ActivityTraderLogin::class.java))
            finish()
        }

        linearAlreadyRegistered.setOnClickListener {
            startActivity(Intent(this@ActivityThankyou, ActivityTraderLogin::class.java))
            finish()
        }
    }

    private fun initViews() {
        btnDone = findViewById(R.id.btn_thankyou_done)
        linearAlreadyRegistered = findViewById(R.id.linear_trader_already_registered)
    }

    override fun onStart() {
        super.onStart()
        val pref = getSharedPreferences("reg_progress", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("current_screen", "reg_complete")
        editor.apply()
    }

    override fun onBackPressed() {
        startActivity(Intent(this@ActivityThankyou, ActivityTraderLogin::class.java))
        finish()
    }

}
