package com.example.kioskupgrade.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kioskupgrade.adapter.MenuAdapter
import com.example.kioskupgrade.adapter.StockAdapter
import com.example.kioskupgrade.databinding.FragmentBeverageOrderBinding


//주문화면의 음료 탭 제어

class BeverageOrderFragment: Fragment() {
    lateinit var binding : FragmentBeverageOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeverageOrderBinding.inflate(inflater, container, false)

        //beverage		생수
        //beverage2	    아메리카노
        //beverage3	    콜라
        //beverage4	    사이다

        val dataSet = mutableListOf<String>()
        dataSet.add("생수")
        dataSet.add("콜라")
        dataSet.add("사이다")

        val layoutManager = GridLayoutManager(binding.root.context, 3,
            GridLayoutManager.VERTICAL, false)

        binding.recyclerView2.layoutManager = layoutManager
        binding.recyclerView2.adapter = MenuAdapter(dataSet)

//        binding.recyclerView2.addItemDecoration(
//            DividerItemDecoration(binding.root.context,
//                LinearLayoutManager.VERTICAL)
//        )

        dataSet.add("아메리카노")
        (binding.recyclerView2.adapter as MenuAdapter).notifyDataSetChanged()

        return binding.root
    }
}