package com.idmdragon.fishery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, Class.forName("com.idmdragon.feature.ui.home.HomeActivity"))).also {
            finish()
        }
    }
}