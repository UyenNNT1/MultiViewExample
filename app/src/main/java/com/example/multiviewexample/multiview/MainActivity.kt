package com.example.multiviewexample.multiview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multiviewexample.R
import com.example.multiviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mList: ArrayList<DataItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.mainRecyclerView.setHasFixedSize(true)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)

        mList = ArrayList()
        prepareData()

        val adapter = MainAdapter(mList)
        binding.mainRecyclerView.adapter = adapter
    }

    private fun prepareData() {
        //best seller
        val bestSellerList = ArrayList<RecyclerItem>()
        bestSellerList.add(RecyclerItem(R.drawable.img_clothing_bags, "Up to 20%"))
        bestSellerList.add(RecyclerItem(R.drawable.img_mobiles, "Up to 10%"))
        bestSellerList.add(RecyclerItem(R.drawable.img_watches, "Up to 50%"))
        bestSellerList.add(RecyclerItem(R.drawable.img_clothing_bags, "Up to 70%"))
        bestSellerList.add(RecyclerItem(R.drawable.img_mobiles, "Up to 30%"))
        bestSellerList.add(RecyclerItem(R.drawable.img_watches, "Up to 40%"))

        //clothing
        val clothingList = ArrayList<RecyclerItem>()
        clothingList.add(RecyclerItem(R.drawable.img_offer_levis, "Up to 25% off"))
        clothingList.add(RecyclerItem(R.drawable.img_clothing_women, "Up to 25% off"))
        clothingList.add(RecyclerItem(R.drawable.img_shoes, "Up to 25% off"))
        clothingList.add(RecyclerItem(R.drawable.img_offer_levis, "Up to 25% off"))
        clothingList.add(RecyclerItem(R.drawable.img_clothing_women, "Up to 25% off"))
        clothingList.add(RecyclerItem(R.drawable.img_shoes, "Up to 25% off"))

        mList.add(DataItem(DataItemType.BEST_SELLER, bestSellerList))
        mList.add(DataItem(DataItemType.BANNER, Banner(R.drawable.img_offer_tv)))
        mList.add(DataItem(DataItemType.CLOTHING, clothingList))
        mList.add(DataItem(DataItemType.BANNER, Banner(R.drawable.img_shoes)))
        mList.add(DataItem(DataItemType.BEST_SELLER, bestSellerList.asReversed()))
        mList.add(DataItem(DataItemType.BANNER, Banner(R.drawable.img_offer_camera)))
    }
}