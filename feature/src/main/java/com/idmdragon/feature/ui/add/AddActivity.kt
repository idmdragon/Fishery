package com.idmdragon.feature.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.domain.utils.Resource
import com.idmdragon.feature.R
import com.idmdragon.feature.databinding.ActivityAddBinding
import com.idmdragon.feature.di.featureModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private val viewModel: AddViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(featureModule)
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getAllArea().observe(this@AddActivity) { resource ->
            when (resource) {
                is Resource.Success -> {
                    val listAreaCity = mutableListOf<String>()
                    val listAreaProvince = mutableListOf<String>()
                    resource.data?.let {
                        it.map { area ->
                            listAreaCity.add(area.city)
                            listAreaProvince.add(area.province)
                        }
                        viewModel.setAreaCity(listAreaCity)
                        viewModel.setAreaProvince(listAreaProvince)
                    }
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

        viewModel.getAreaCity().observe(this) { listCity ->
            binding.autoCompleteCity.setAdapter(
                ArrayAdapter(
                    this@AddActivity,
                    R.layout.category_list_dropdown_menu,
                    listCity as ArrayList
                )
            )
        }

        viewModel.getAreaProvince().observe(this) { listProvince ->
            binding.autoCompleteProvince.setAdapter(
                ArrayAdapter(
                    this@AddActivity,
                    R.layout.category_list_dropdown_menu,
                    listProvince as ArrayList
                )
            )
        }
    }

}