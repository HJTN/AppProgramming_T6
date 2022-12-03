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
import com.example.kioskupgrade.databinding.FragmentHamburgerOrderBinding

//주문화면의 햄버거, 치킨 탭 제어

class HamburgerOrderFragment: Fragment() {
    lateinit var binding : FragmentHamburgerOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHamburgerOrderBinding.inflate(inflater, container, false)

        val dataSet = mutableListOf<String>()

        //burger		머쉬룸 버거
        //burger2		새우 버거
        //burger3		핫 치킨 버거
        //burger4		에그 버거
        //burger5		불고기 버거
        //burger6		치킨 버거
        //burger7		치즈 버거
        //burger8		피쉬 버거

        //chicken2		스노우 어니언 치킨
        //chicken3		간장치킨
        //chicken4		양념치킨
        //chicken5		파닭
        //chicken6		후라이드



        dataSet.add("불고기 버거")
        dataSet.add("치킨 버거")
        dataSet.add("핫치킨 버거")
        dataSet.add("새우 버거")
        dataSet.add("피쉬 버거")
        dataSet.add("치즈 버거")
        dataSet.add("머쉬룸 버거")


        val layoutManager = GridLayoutManager(binding.root.context, 3,
            GridLayoutManager.VERTICAL, false)

        binding.recyclerView2.layoutManager = layoutManager
        binding.recyclerView2.adapter = MenuAdapter(dataSet)

//        binding.recyclerView2.addItemDecoration(
//            DividerItemDecoration(binding.root.context,
//                LinearLayoutManager.VERTICAL)
//        )

        dataSet.add("에그 버거")
        (binding.recyclerView2.adapter as MenuAdapter).notifyDataSetChanged()

        return binding.root
    }
}