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
    val dataSet = mutableListOf<Item>()
    var root = "Stock_DB/Single/Beverage"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeverageOrderBinding.inflate(inflater, container, false)


        dataSet.add(Item(root+"/CocaCola","코카 콜라", 1800, R.drawable.cocacola,100))
        dataSet.add(Item(root+"/Fanta","환타", 1800, R.drawable.fanta,80))
        dataSet.add(Item(root+"/Sprite","스프라이트", 1800, R.drawable.sprite,0))
        dataSet.add(Item(root+"/Water","생수", 1200, R.drawable.water,0))

        val layoutManager = GridLayoutManager(binding.root.context, 3,
            GridLayoutManager.VERTICAL, false)

        binding.recyclerView2.layoutManager = layoutManager
        binding.recyclerView2.adapter = MenuAdapter(dataSet)

        (binding.recyclerView2.adapter as MenuAdapter).notifyDataSetChanged()

        return binding.root
    }
}