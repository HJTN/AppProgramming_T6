package com.example.kioskupgrade.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskupgrade.databinding.SelectedmenuItemBinding
import com.example.kioskupgrade.databinding.StockItemBinding

data class SelectedMenu(
    var name : String,
    var price : Int,
    var cnt : Int
)

class SelectedViewHolder(val binding: SelectedmenuItemBinding): RecyclerView.ViewHolder(binding.root)

class SelectedMenuAdapter(val dataSet: MutableList<SelectedMenu>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SelectedViewHolder(SelectedmenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("RecyclerView", "onBindViewHolder(): $position")
        val binding = (holder as SelectedViewHolder).binding

        binding.itemName.text = dataSet[position].name
        binding.itemPrice.text = dataSet[position].price.toString()
        binding.itemCnt.text = dataSet[position].cnt.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}