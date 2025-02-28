package com.example.multiviewexample.multiview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multiviewexample.R
import com.example.multiviewexample.databinding.EachItemBinding
import com.example.multiviewexample.databinding.LayoutItemBannerBinding

class MainAdapter(private val dataItemList: List<DataItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class BannerItemViewHolder(private val binding: LayoutItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindBannerRecyclerView(banner: Banner) {
            binding.bannerIv.setImageResource(banner.image)
        }

    }

    inner class RecyclerItemViewHolder(private val binding: EachItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.childRecyclerView.setHasFixedSize(true)
            binding.childRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bindClothingRecyclerView(recyclerItemList: List<RecyclerItem>) {
            val adapter = ChildAdapter(DataItemType.BEST_SELLER, recyclerItemList)
            binding.childRecyclerView.adapter = adapter
        }

        fun bindBestSellerRecyclerView(recyclerItemList: List<RecyclerItem>) {
            val adapter = ChildAdapter(DataItemType.CLOTHING, recyclerItemList)
            binding.childRecyclerView.adapter = adapter
        }

    }

    override fun getItemViewType(position: Int): Int {
        when (dataItemList[position].viewType) {
            DataItemType.BANNER -> {
                return R.layout.layout_item_banner
            }

            else -> {
                return R.layout.each_item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            R.layout.layout_item_banner -> {
                val binding = LayoutItemBannerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BannerItemViewHolder(binding)
            }

            else -> {
                val binding =
                    EachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RecyclerItemViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerItemViewHolder -> {
                dataItemList[position].banner?.let { holder.bindBannerRecyclerView(it) }
            }

            else -> {
                when (dataItemList[position].viewType) {
                    DataItemType.BEST_SELLER -> {
                        dataItemList[position].recyclerItemList?.let {
                            (holder as RecyclerItemViewHolder).bindBestSellerRecyclerView(it)
                        }
                    }

                    else -> {
                        dataItemList[position].recyclerItemList?.let {
                            (holder as RecyclerItemViewHolder).bindClothingRecyclerView(it)
                        }
                    }
                }
            }
        }
    }
}