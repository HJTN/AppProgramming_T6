package com.example.kioskupgrade.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kioskupgrade.DTO.Item
import com.example.kioskupgrade.MenuAdapter
import com.example.kioskupgrade.R
import com.example.kioskupgrade.databinding.FragmentBeverageOrderBinding


//주문화면의 음료 탭 제어
class BeverageOrderFragment: Fragment(){
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

        val dataSet = mutableListOf<Item>()
        dataSet.add(Item("생수", "800 원", R.drawable.beverage))
        dataSet.add(Item("콜라", "1500 원", R.drawable.beverage3))
        dataSet.add(Item("사이다", "1500 원", R.drawable.beverage4))
        dataSet.add(Item("아메리카노", "1800 원", R.drawable.beverage2))

        val layoutManager = GridLayoutManager(binding.root.context, 3,
            GridLayoutManager.VERTICAL, false)

        binding.recyclerView2.layoutManager = layoutManager
        binding.recyclerView2.adapter = MenuAdapter(dataSet)

//        binding.recyclerView2.addItemDecoration(
//            DividerItemDecoration(binding.root.context,
//                LinearLayoutManager.VERTICAL)
//        )
        (binding.recyclerView2.adapter as MenuAdapter).notifyDataSetChanged()

        return binding.root
    }
}