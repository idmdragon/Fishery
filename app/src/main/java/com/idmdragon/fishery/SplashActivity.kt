package com.idmdragon.fishery

import android.annotation.SuppressLint
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.idmdragon.fishery.di.splashModule
import com.idmdragon.fishery.ui.splash.SplashViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadKoinModules(splashModule)
        cleanDataState()
        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            startActivity(
                Intent(
                    this@SplashActivity,
                    Class.forName("com.idmdragon.feature.ui.home.HomeActivity")
                )
            ).also {
                finish()
            }
        }
    }

    private fun cleanDataState() {
        if (isNetworkConnected()) {
            viewModel.clearFishery()
        }
    }

    @Suppress("DEPRECATION")
    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}