package com.example.teamprogect.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kioskupgrade.DTO.Item
import com.example.kioskupgrade.DTO.Sale
import com.example.kioskupgrade.MenuAdapter
import com.example.kioskupgrade.R
import com.example.kioskupgrade.databinding.FragmentRecommendOrderBinding

//주문화면의 추천매뉴 탭 제어

class RecommendOrderFragment: Fragment() {
    lateinit var binding : FragmentRecommendOrderBinding
    val dataSet = mutableListOf<Item>()
    var root = "Stock_DB"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendOrderBinding.inflate(inflater, container, false)

        // 세트
        dataSet.add(Item(root+"/Set/BigMac","빅맥 세트", 5900, R.drawable.bigmacburgerset))
        dataSet.add(Item(root+"/Set/QuarterPounderCheese","쿼터파운더 치즈 세트", 6700, R.drawable.quarterpoundercheeseburgerset))
        dataSet.add(Item(root+"/Set/DoubleBeefMeetChiliBurger","더블 비프 미트칠리 버거 세트", 9500, R.drawable.doublebeefmeetchiliburgerset))
        dataSet.add(Item(root+"/Set/DoubleBulgogiBurger","더블 불고기 버거 세트", 5500, R.drawable.doublebulgogiburgerset))
        // 단품
        dataSet.add(Item(root+"/Single/Burger/BigMac","빅맥", 4600, R.drawable.bigmacburger))
        dataSet.add(Item(root+"/Single/Burger/QuarterPounderCheese","쿼터파운더 치즈", 5200, R.drawable.quarterpoundercheeseburger))
        dataSet.add(Item(root+"/Single/Beverage/CocaCola","코카 콜라", 1800, R.drawable.cocacola))
        dataSet.add(Item(root+"/Single/Beverage/Fanta","환타", 1800, R.drawable.fanta))
        dataSet.add(Item(root+"/Single/SideMenu/FrenchFries","후렌치 후라이", 1700, R.drawable.frenchfries))
        dataSet.add(Item(root+"/Single/SideMenu/McNuggets","맥너겟", 1800, R.drawable.mcnuggets))
        dataSet.add(Item(root+"/Single/SideMenu/GoldenMozzarellaCheeseSticks","골든 모짜렐라 치즈 스틱", 2200, R.drawable.goldenmozzarellacheesesticks))

        val layoutManager = GridLayoutManager(binding.root.context, 3,
            GridLayoutManager.VERTICAL, false)

        binding.recyclerView2.layoutManager = layoutManager
        binding.recyclerView2.adapter = MenuAdapter(dataSet)

        (binding.recyclerView2.adapter as MenuAdapter).notifyDataSetChanged()

        return binding.root
    }
}