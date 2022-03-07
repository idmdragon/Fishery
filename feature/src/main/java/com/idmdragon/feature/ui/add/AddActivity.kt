package com.idmdragon.feature.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.domain.utils.ConverterHelper.convertMillisToString
import com.idmdragon.domain.utils.Resource
import com.idmdragon.feature.R
import com.idmdragon.feature.databinding.ActivityAddBinding
import com.idmdragon.feature.di.featureModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import java.util.*
import kotlin.collections.ArrayList

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private val viewModel: AddViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(featureModule)
        setupObserver()
        setupListener()
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
                    binding.progressBar.isVisible = true
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

        viewModel.getAllSize().observe(this) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        binding.progressBar.isVisible = false
                        val listSize = ArrayList<String>()
                        it.map { size ->
                            listSize.add(size.size)
                        }
                        binding.autoCompleteSize.setAdapter(
                            ArrayAdapter(
                                this@AddActivity,
                                R.layout.category_list_dropdown_menu,
                                listSize
                            )
                        )
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    Snackbar.make(
                        binding.root,
                        resource.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setupListener() {
        binding.apply {
            btnAdd.setOnClickListener {
                if (tilKomoditas.editText?.text.toString().isNotEmpty()
                    && tilPrice.editText?.text.toString().isNotEmpty()
                    && tilCityArea.editText?.text.toString().isNotEmpty()
                    && tilProvinceArea.editText?.text.toString().isNotEmpty()
                    && tilSize.editText?.text.toString().isNotEmpty()
                ){
                    viewModel.addFishery(
                        uuid = UUID.randomUUID().toString(),
                        commodity = tilKomoditas.editText?.text.toString(),
                        price = tilPrice.editText?.text.toString(),
                        areaCity = tilCityArea.editText?.text.toString(),
                        areaProvince = tilProvinceArea.editText?.text.toString(),
                        size = tilSize.editText?.text.toString(),
                        tgl_parsed = convertMillisToString(System.currentTimeMillis()),
                        timestamp = System.currentTimeMillis().toString()
                    ).observe(this@AddActivity,::addFisheryObserver)

                }else{
                    Snackbar.make(
                        binding.root,
                        getString(R.string.feature_msg_error),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun addFisheryObserver(resource: Resource<String>?) {
        when (resource) {
            is Resource.Success -> {
                binding.progressBar.isVisible = false
                Snackbar.make(
                    binding.root,
                    getString(R.string.feature_msg_success_added),
                    Snackbar.LENGTH_LONG
                ).show()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1500)
                    finish()
                }
            }
            is Resource.Loading -> {
                binding.progressBar.isVisible = true
            }

            is Resource.Error -> {
                binding.progressBar.isVisible = false
                Snackbar.make(
                    binding.root,
                    resource.message.toString(),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

}