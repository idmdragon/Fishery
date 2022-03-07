package com.idmdragon.feature.ui.filter

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.domain.utils.Resource
import com.idmdragon.feature.R
import com.idmdragon.feature.databinding.ActivityFilterBinding
import com.idmdragon.feature.di.featureModule
import com.idmdragon.feature.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterBinding
    private val viewModel: FilterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(featureModule)
        setupObserver()
        setupListener()
    }

    private fun setupObserver() {
        viewModel.getAllArea().observe(this@FilterActivity) { resource ->
            when (resource) {
                is Resource.Success -> {
                    var index = 0
                    resource.data?.let { listArea ->
                        listArea.map { area ->
                            val radioButton = RadioButton(this@FilterActivity)
                            radioButton.id = index
                            radioButton.text = getString(
                                R.string.feature_text_location,
                                area.city.lowercase().replaceFirstChar { it.uppercase() },
                                area.province.lowercase().replaceFirstChar { it.uppercase() }
                            )
                            binding.rgArea.addView(radioButton)
                            binding.rgArea.findViewById<RadioButton>(radioButton.id)
                                .setPadding(0, 16, 0, 16)
                            index++
                        }
                        binding.rgArea.setOnCheckedChangeListener { radioGroup, checkedId ->
                            viewModel.area = radioGroup?.findViewById<RadioButton>(checkedId)?.text.toString()
                        }
                    }
                }
                is Resource.Loading -> {}

                is Resource.Error -> {
                    Snackbar.make(
                        binding.root,
                        resource.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewModel.getAllSize().observe(this) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listSize ->
                        var index = 100
                        listSize.map { size ->
                            val radioButton = RadioButton(this@FilterActivity)
                            radioButton.id = index
                            radioButton.text = size.size
                            binding.rgSize.addView(radioButton)
                            binding.rgSize.findViewById<RadioButton>(radioButton.id)
                                .setPadding(0, 16, 0, 16)
                            index++
                        }
                        binding.rgSize.setOnCheckedChangeListener { radioGroup, checkedId ->
                            viewModel.size = radioGroup?.findViewById<RadioButton>(checkedId)?.text.toString()
                        }
                    }
                }
                is Resource.Loading -> {}

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

    private fun setupListener(){
        binding.apply {
            btnFilter.setOnClickListener {
                finishResult()
            }
            ibBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
    private fun finishResult(){
        val intent = Intent()
            .putExtra(HomeActivity.EXTRAS_AREA, viewModel.area)
            .putExtra(HomeActivity.EXTRAS_SIZE, viewModel.size)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}