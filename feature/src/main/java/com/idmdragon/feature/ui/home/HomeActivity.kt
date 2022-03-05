package com.idmdragon.feature.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.utils.Resource
import com.idmdragon.feature.databinding.ActivityHomeBinding
import com.idmdragon.feature.di.featureModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(featureModule)

        viewModel.getAllFishery().observe(this){ resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { populateData(it) }
                }
                is Resource.Loading -> {

                }

                is Resource.Error -> {
                    Snackbar.make(
                        binding.root,
                        resource.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }

        }
    }

    private fun populateData(data: List<Fishery>){
            Log.d("CEK","data $data")
    }
}