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
import com.example.kioskupgrade.databinding.FragmentHamburgerOrderBinding

//주문화면의 햄버거, 치킨 탭 제어

class HamburgerOrderFragment: Fragment() {
    lateinit var binding : FragmentHamburgerOrderBinding
    val dataSet = mutableListOf<Item>()
    var root = "Stock_DB"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHamburgerOrderBinding.inflate(inflater, container, false)

        // 단품
        dataSet.add(Item(root+"/Single/Burger/BigMac","빅맥", 4600, R.drawable.bigmacburger, 100))
        dataSet.add(Item(root+"/Single/Burger/QuarterPounderCheese","쿼터파운더 치즈", 5200, R.drawable.quarterpoundercheeseburger, 100))
        dataSet.add(Item(root+"/Single/Burger/DoubleBulgogiBurger","더블 불고기 버거", 4400, R.drawable.bigmacburger,90))
        dataSet.add(Item(root+"/Single/Burger/DoubleBeefMeetChiliBurger","더블 비프 미트칠리 버거", 8300, R.drawable.quarterpoundercheeseburger,80))
        // 세트
        dataSet.add(Item(root+"/Set/BigMac","빅맥 세트", 5900, R.drawable.bigmacburgerset,80))
        dataSet.add(Item(root+"/Set/QuarterPounderCheese","쿼터파운더 치즈 세트", 6700, R.drawable.quarterpoundercheeseburgerset,0))
        dataSet.add(Item(root+"/Set/DoubleBeefMeetChiliBurger","더블 비프 미트칠리 버거 세트", 9500, R.drawable.doublebeefmeetchiliburgerset,0))
        dataSet.add(Item(root+"/Set/DoubleBulgogiBurger","더블 불고기 버거 세트", 5500, R.drawable.doublebulgogiburgerset,0))

        val layoutManager = GridLayoutManager(binding.root.context, 3,
            GridLayoutManager.VERTICAL, false)

        binding.recyclerView2.layoutManager = layoutManager
        binding.recyclerView2.adapter = MenuAdapter(dataSet)

        (binding.recyclerView2.adapter as MenuAdapter).notifyDataSetChanged()

        return binding.root
    }
}
