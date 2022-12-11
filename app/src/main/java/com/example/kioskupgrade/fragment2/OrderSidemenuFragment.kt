package com.example.kioskupgrade.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kioskupgrade.Item
import com.example.kioskupgrade.R
import com.example.kioskupgrade.adapter.MenuAdapter
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

        val dataSet = mutableListOf<Item>()
        dataSet.add(Item("치킨너겟", "2200 원", R.drawable.sidemenu))
        dataSet.add(Item("감자튀김", "2000 원", R.drawable.sidemenu2))
        dataSet.add(Item("치즈스틱", "2200 원", R.drawable.sidemenu3))
        dataSet.add(Item("소프트콘", "800 원", R.drawable.sidemenu4))
        dataSet.add(Item("밀크쉐이크", "1800 원", R.drawable.sidemenu5))

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