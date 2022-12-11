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
import com.example.kioskupgrade.databinding.FragmentSidemenuOrderBinding


//주문화면의 사이드메뉴 탭 제어

class SidemenuOrderFragment: Fragment() {
    lateinit var binding : FragmentSidemenuOrderBinding
    val dataSet = mutableListOf<Item>()
    var root = "Stock_DB/Single/SideMenu"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSidemenuOrderBinding.inflate(inflater, container, false)

        dataSet.add(Item(root+"/FrenchFries","후렌치 후라이", 1700, R.drawable.frenchfries))
        dataSet.add(Item(root+"/GoldenMozzarellaCheeseSticks","골든 모짜렐라 치즈 스틱", 2200, R.drawable.goldenmozzarellacheesesticks))
        dataSet.add(Item(root+"/McNuggets","맥너겟", 1800, R.drawable.mcnuggets))
        dataSet.add(Item(root+"/ShanghaiChickenSnackWrap","상하이 치킨 스낵랩", 2000, R.drawable.shanghaichickensnackwrap))

        val layoutManager = GridLayoutManager(binding.root.context, 3,
            GridLayoutManager.VERTICAL, false)

        binding.recyclerView2.layoutManager = layoutManager
        binding.recyclerView2.adapter = MenuAdapter(dataSet)

        (binding.recyclerView2.adapter as MenuAdapter).notifyDataSetChanged()

        return binding.root
    }
}