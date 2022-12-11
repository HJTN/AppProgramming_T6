package com.example.kioskupgrade.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kioskupgrade.DTO.Material
import com.example.kioskupgrade.R
import com.example.kioskupgrade.databinding.StockEditBinding
import com.example.kioskupgrade.databinding.StockItemBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.DecimalFormat

class StockViewHolder(val binding: StockItemBinding): RecyclerView.ViewHolder(binding.root)

class StockAdapter(val dataSet: MutableList<Material>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        inflater = LayoutInflater.from(parent.context)
        return StockViewHolder(StockItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as StockViewHolder).binding
        val dialogBinding = StockEditBinding.inflate(inflater)
        val decimalFormat = DecimalFormat("#,###")

        binding.itemImg.setImageResource(dataSet[position].img)
        binding.itemName.text = dataSet[position].name
        binding.itemNum.text = "x " + decimalFormat.format(dataSet[position].num)

        binding.itemEdit.setOnClickListener {
            dialogBinding.materImg.setImageResource(dataSet[position].img)
            dialogBinding.materName.text = dataSet[position].name

            AlertDialog.Builder(binding.root.context).run {
                setView(dialogBinding.root)
                setPositiveButton("변경", DialogInterface.OnClickListener { dialogInterface, i ->
                    val editedNum = dialogBinding.materNumEdit.text.toString().toInt()

                    binding.itemNum.text = "x " + decimalFormat.format(editedNum)

                    dataSet[position].setNum(editedNum)

                    var updateData = mutableMapOf<String, Any>()
                    updateData.put("num", editedNum)
                    Firebase.database.reference.child(dataSet[position].key).updateChildren(updateData)

                    Toast.makeText(binding.root.context, "${binding.itemName.text} x ${editedNum}", Toast.LENGTH_SHORT).show()
                })
                setNegativeButton("취소", null)
                show()
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun getItems(): MutableList<Material> {
        return dataSet
    }
}