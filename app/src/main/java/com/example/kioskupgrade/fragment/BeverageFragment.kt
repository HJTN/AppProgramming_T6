package com.example.kioskupgrade.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kioskupgrade.adapter.StockAdapter
import com.example.kioskupgrade.databinding.FragmentBeverageBinding


class BeverageFragment: Fragment() {
    lateinit var binding : FragmentBeverageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeverageBinding.inflate(inflater, container, false)

        val dataSet = mutableListOf<String>()
        for (i in 1..10)
            dataSet.add("Material $i")

        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        binding.recyclerView.adapter = StockAdapter(dataSet)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.root.context,
                LinearLayoutManager.VERTICAL)
        )

        dataSet.add("Material 11")
        (binding.recyclerView.adapter as StockAdapter).notifyDataSetChanged()

        return binding.root
    }
}