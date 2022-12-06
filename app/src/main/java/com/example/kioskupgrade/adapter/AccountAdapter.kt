package com.example.kioskupgrade.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskupgrade.DTO.Sale
import com.example.kioskupgrade.databinding.AccountItemBinding

class AccountViewHolder(val binding: AccountItemBinding): RecyclerView.ViewHolder(binding.root)

class AccountAdapter(val dataSet: MutableList<Sale>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AccountViewHolder(AccountItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("RecyclerView", "onBindViewHolder(): $position")
        val binding = (holder as StockViewHolder).binding

        binding.itemName.text = dataSet[position].name
        binding.itemNum.text = "x ${dataSet[position].num}"

        binding.itemRoot.setOnClickListener {
            Toast.makeText(binding.root.context, "${binding.itemName.text} Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}