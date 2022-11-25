package com.example.kioskupgrade.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskupgrade.databinding.StockItemBinding

class StockViewHolder(val binding: StockItemBinding): RecyclerView.ViewHolder(binding.root)

class StockAdapter(val dataSet: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StockViewHolder(StockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("RecyclerView", "onBindViewHolder(): $position")
        val binding = (holder as StockViewHolder).binding

        binding.itemName.text = dataSet[position]
        binding.itemNum.text = "x $position"

        binding.itemRoot.setOnClickListener {
            Toast.makeText(binding.root.context, "${binding.itemName.text} Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}