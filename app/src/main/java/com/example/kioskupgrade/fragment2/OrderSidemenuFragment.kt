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
import com.example.kioskupgrade.databinding.FragmentSidemenuOrderBinding


//주문화면의 사이드메뉴 탭 제어

class SidemenuOrderFragment: Fragment() {
    lateinit var binding : FragmentSidemenuOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSidemenuOrderBinding.inflate(inflater, container, false)

        //sidemenu		치킨너겟
        //sidemenu2	    감자튀김
        //sidemenu3	    치즈스틱
        //sidemenu4	    소프트콘
        //sidemenu5	    밀크쉐이크

        val dataSet = mutableListOf<String>()
        dataSet.add("감자 튀김")
        dataSet.add("치킨 너겟")
        dataSet.add("치즈 스틱")
        dataSet.add("소프트 콘")


        val layoutManager = GridLayoutManager(binding.root.context, 3,
            GridLayoutManager.VERTICAL, false)

        binding.recyclerView2.layoutManager = layoutManager
        binding.recyclerView2.adapter = MenuAdapter(dataSet)

//        binding.recyclerView2.addItemDecoration(
//            DividerItemDecoration(binding.root.context,
//                LinearLayoutManager.VERTICAL)
//        )

        dataSet.add("밀크 쉐이크")
        (binding.recyclerView2.adapter as MenuAdapter).notifyDataSetChanged()

        return binding.root
    }
}