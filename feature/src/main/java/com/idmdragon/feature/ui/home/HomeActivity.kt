package com.idmdragon.feature.ui.home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.idmdragon.domain.model.Area
import com.idmdragon.domain.model.Fishery
import com.idmdragon.domain.utils.Resource
import com.idmdragon.feature.databinding.ActivityHomeBinding
import com.idmdragon.feature.di.featureModule
import com.idmdragon.feature.ui.adapter.HomeAdapter
import com.idmdragon.feature.ui.add.AddActivity
import com.idmdragon.feature.ui.filter.FilterActivity
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

            ibFilter.setOnClickListener {
                startForResult.launch(Intent(this@HomeActivity, FilterActivity::class.java))
            }
        }
    }

    private fun setupObserver() {
        viewModel.getAllFishery().observe(this){
            observerFishery(it)
            viewModel.getAllFishery().removeObservers(this)
        }

        binding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getAllFishery().removeObservers(this@HomeActivity)
                viewModel.searchItem(binding.etSearch.text.toString()).observe(this, ::observerFishery)
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun observerFishery(resource: Resource<List<Fishery>>?) {
        when (resource) {
            is Resource.Success -> {
                binding.progressBar.isVisible = false
                viewModel.getAllFishery().removeObservers(this)
                listAdapter.setItems(listOf())
                resource.data?.let { listAdapter.setItems(it) }
                binding.rvFishery.adapter = listAdapter

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

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val intentArea = intent?.getStringExtra(EXTRAS_AREA)
                if (intentArea != null) {
                    val areaSplit = intentArea.split(" , ").toTypedArray()
                    filterList(
                        intent.getStringExtra(EXTRAS_SIZE).orEmpty(),
                        Area(
                            areaSplit[0].uppercase(),
                            areaSplit[1].uppercase()
                        )
                    )
                } else {
                    filterList(
                        intent?.getStringExtra(EXTRAS_SIZE).orEmpty(),
                        Area(
                            "",
                            ""
                        )
                    )
                }
            }
        }

    private fun filterList(size: String, area: Area) {
        viewModel.getFilteredFishery(size, area).observe(this, ::observerFishery)
    }

    companion object {
        const val EXTRAS_AREA = "extras_area"
        const val EXTRAS_SIZE = "extras_size"
    }
}