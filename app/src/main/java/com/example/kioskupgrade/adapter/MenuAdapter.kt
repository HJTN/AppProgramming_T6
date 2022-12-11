package com.example.kioskupgrade.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskupgrade.DTO.Item
import com.example.kioskupgrade.databinding.MenuItemBinding

//주문화면의 리사이클러뷰를 위한 어댑터

class MenuViewHolder(val binding: MenuItemBinding): RecyclerView.ViewHolder(binding.root)

class MenuAdapter(val dataSet: MutableList<Item>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MenuViewHolder(MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("RecyclerView", "onBindViewHolder(): $position")
        val binding = (holder as MenuViewHolder).binding

        binding.itemName.text = dataSet[position].name
        binding.itemPrice.text = dataSet[position].price
        binding.itemImg.setImageResource(dataSet[position].img)

        binding.itemButton.setOnClickListener {
            Toast.makeText(binding.root.context, "${binding.itemName.text} Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}