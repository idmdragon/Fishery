package com.idmdragon.feature.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idmdragon.domain.model.Fishery
import com.idmdragon.feature.databinding.ItemFisheryBinding


class HomeAdapter (private val context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val items = arrayListOf<Fishery>()

    fun setItems(items: List<Fishery>) {
        this.items.clear()
        this.items.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val itemBinding =
            ItemFisheryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemFisheryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Fishery) {
            with(binding) {
                item.apply {
                    tvDate.text = tgl_parsed
                    tvKomoditas.text = komoditas
                    tvLocation.text = area_kota+","+area_provinsi
                    tvPrice.text = price
                    tvSize.text = size
                }
            }
        }
    }
}