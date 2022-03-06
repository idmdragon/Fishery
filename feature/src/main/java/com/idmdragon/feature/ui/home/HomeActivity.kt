package com.idmdragon.feature.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.utils.Resource
import com.idmdragon.feature.databinding.ActivityHomeBinding
import com.idmdragon.feature.di.featureModule
import com.idmdragon.feature.ui.adapter.HomeAdapter
import com.idmdragon.feature.ui.add.AddActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val listAdapter: HomeAdapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(featureModule)
        setupObserver()
        setupListener()
    }

    private fun setupListener() {
        binding.apply {
            fabAdd.setOnClickListener {
                startActivity(Intent(this@HomeActivity, AddActivity::class.java))
            }
        }
    }

    private fun setupObserver() {
        viewModel.getAllFishery().observe(this,::observerFishery)

        binding.etSearch.doAfterTextChanged {
            if (it.toString().isBlank()) {
                viewModel.getAllFishery().observe(this,::observerFishery)
            } else {
                viewModel.searchItem(it.toString()).observe(this,::observerFishery)
            }
        }
    }

    private fun observerFishery(resource: Resource<List<Fishery>>?) {
        when (resource) {
            is Resource.Success -> {
                binding.progressBar.isVisible = false
                resource.data?.let { populateData(it) }
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


    private fun populateData(data: List<Fishery>) {
        binding.apply {
            rvFishery.adapter = listAdapter
            binding.rvFishery.layoutManager = LinearLayoutManager(
                this@HomeActivity,
                LinearLayoutManager.VERTICAL, false
            )
            data.let { listAdapter.setItems(it) }
        }
    }

}