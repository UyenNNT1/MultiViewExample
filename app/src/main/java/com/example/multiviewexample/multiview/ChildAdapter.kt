package com.example.multiviewexample.multiview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multiviewexample.databinding.LayoutItemBestsellerBinding
import com.example.multiviewexample.databinding.LayoutItemClothingBinding

class ChildAdapter(private val viewType: Int, private val recyclerItemList: List<RecyclerItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class BessSellerViewHolder(private val binding: LayoutItemBestsellerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindBestSellerView(recyclerItem: RecyclerItem) {
            binding.bestSellerImage.setImageResource(recyclerItem.image)
            binding.bestSellerText.text = recyclerItem.offer
        }
    }

    inner class ClothingViewHolder(private val binding: LayoutItemClothingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindClothingView(recyclerItem: RecyclerItem) {
            binding.clothingImage.setImageResource(recyclerItem.image)
            binding.clothingOfferTv.text = recyclerItem.offer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            DataItemType.BEST_SELLER -> {
                val binding = LayoutItemBestsellerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return BessSellerViewHolder(binding)
            }

            else -> {
                val binding = LayoutItemClothingBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return ClothingViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BessSellerViewHolder -> holder.bindBestSellerView(recyclerItemList[position])
            is ClothingViewHolder -> holder.bindClothingView(recyclerItemList[position])
        }
    }

}