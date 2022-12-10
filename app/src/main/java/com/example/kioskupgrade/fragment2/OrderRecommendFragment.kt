package com.example.teamprogect.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kioskupgrade.Item
import com.example.kioskupgrade.R
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

        val dataSet = mutableListOf<Item>()

        dataSet.add(Item("불고기 버거 세트", "5400 원", R.drawable.setburger1))
        dataSet.add(Item("치킨 버거 세트", "5400 원", R.drawable.setburger2))
        dataSet.add(Item("새우 버거 세트", "5400 원", R.drawable.setburger3))
        dataSet.add(Item("머쉬룸 버거 세트", "5300 원", R.drawable.setburger4))
        dataSet.add(Item("에그버거 세트", "5200 원", R.drawable.setburger5))

        dataSet.add(Item("후라이드 치킨\n세트", "16000 원", R.drawable.setchicken1))
        dataSet.add(Item("양념 치킨 세트", "17000 원", R.drawable.setchicken2))
        dataSet.add(Item("간장 치킨 세트", "17000 원", R.drawable.setchicken3))
        dataSet.add(Item("파닭 세트", "19000 원", R.drawable.setchicken4))
        dataSet.add(Item("어니언 스노우윙\n치킨 세트", "18000 원", R.drawable.setchicken5))

        val layoutManager = GridLayoutManager(binding.root.context, 3,
            GridLayoutManager.VERTICAL, false)

        binding.recyclerView2.layoutManager = layoutManager
        binding.recyclerView2.adapter = MenuAdapter(dataSet)

//        binding.recyclerView2.addItemDecoration(
//            DividerItemDecoration(binding.root.context,
//            LinearLayoutManager.VERTICAL))

        (binding.recyclerView2.adapter as MenuAdapter).notifyDataSetChanged()

        return binding.root
    }
}