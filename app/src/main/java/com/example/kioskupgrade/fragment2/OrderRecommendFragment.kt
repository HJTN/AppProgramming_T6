package com.example.teamprogect.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kioskupgrade.adapter.MenuAdapter
import com.example.kioskupgrade.databinding.FragmentRecommendOrderBinding

//주문화면의 추천매뉴 탭 제어

class RecommendOrderFragment: Fragment() {
    lateinit var binding : FragmentRecommendOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendOrderBinding.inflate(inflater, container, false)

        val dataSet = mutableListOf<String>()

        for (i in 1..10)
            dataSet.add("세트메뉴 $i")

        val layoutManager = GridLayoutManager(binding.root.context, 3,
            GridLayoutManager.VERTICAL, false)

        binding.recyclerView2.layoutManager = layoutManager
        binding.recyclerView2.adapter = MenuAdapter(dataSet)

//        binding.recyclerView2.addItemDecoration(
//            DividerItemDecoration(binding.root.context,
//            LinearLayoutManager.VERTICAL))

        dataSet.add("세트메뉴 11")
        (binding.recyclerView2.adapter as MenuAdapter).notifyDataSetChanged()

        return binding.root
    }
}