package com.example.kioskupgrade.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kioskupgrade.DTO.Sale
import com.example.kioskupgrade.R
import com.example.kioskupgrade.databinding.AccountItemBinding
import java.text.DecimalFormat

class AccountViewHolder(val binding: AccountItemBinding): RecyclerView.ViewHolder(binding.root)

class AccountAdapter(val dataSet: MutableList<Sale>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AccountViewHolder(AccountItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("RecyclerView", "onBindViewHolder(): $position")
        val binding = (holder as AccountViewHolder).binding
        val decimalFormat = DecimalFormat("#,###")

        binding.itemImg.setImageResource(dataSet[position].img)
        binding.itemName.text = dataSet[position].name
        binding.itemNum.text = "x " + decimalFormat.format(dataSet[position].num)
        binding.itemAccount.text = decimalFormat.format(dataSet[position].account)

        binding.itemRoot.setOnClickListener {
            Toast.makeText(binding.root.context, "${binding.itemName.text} x ${binding.itemNum.text} x ${binding.itemAccount.text}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}