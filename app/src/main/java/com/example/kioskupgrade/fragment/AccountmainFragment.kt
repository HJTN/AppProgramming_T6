package com.example.kioskupgrade.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kioskupgrade.DTO.Material
import com.example.kioskupgrade.DTO.Sale
import com.example.kioskupgrade.R
import com.example.kioskupgrade.`interface`.CalcInterface
import com.example.kioskupgrade.adapter.AccountAdapter
import com.example.kioskupgrade.databinding.FragmentAccountmainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AccountmainFragment: Fragment() {
    lateinit var binding : FragmentAccountmainBinding
    lateinit var database : DatabaseReference
    var dataSet = mutableListOf<Sale>()
    var root = "Stock_DB"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountmainBinding.inflate(inflater, container, false)

        // Data 가져오기
        // 단품
        dataSet.add(Sale(root+"/Single/Burger/BigMac","빅맥", R.drawable.bigmacburger,15,4600))
        dataSet.add(Sale(root+"/Single/Burger/QuarterPounderCheese","쿼터파운더 치즈", R.drawable.quarterpoundercheeseburger,15,5200))
        dataSet.add(Sale(root+"/Single/Beverage/CocaCola","코카 콜라", R.drawable.cocacola,15,1800))
        dataSet.add(Sale(root+"/Single/Beverage/Fanta","환타", R.drawable.fanta,15,1800))
        dataSet.add(Sale(root+"/Single/SideMenu/FrenchFries","후렌치 후라이", R.drawable.frenchfries,15,1700))
        dataSet.add(Sale(root+"/Single/SideMenu/McNuggets","맥너겟", R.drawable.mcnuggets,15,1800))
        dataSet.add(Sale(root+"/Single/SideMenu/GoldenMozzarellaCheeseSticks","골든 모짜렐라 치즈 스틱", R.drawable.goldenmozzarellacheesesticks,15,2200))
        // 세트
        dataSet.add(Sale(root+"/Set/BigMac","빅맥 세트", R.drawable.bigmacburgerset,15,5900))
        dataSet.add(Sale(root+"/Set/QuarterPounderCheese","쿼터파운더 치즈 세트", R.drawable.quarterpoundercheeseburgerset,15,6700))
        dataSet.add(Sale(root+"/Set/DoubleBeefMeetChiliBurger","더블 비프 미트칠리 버거 세트", R.drawable.doublebeefmeetchiliburgerset,15,9500))
        dataSet.add(Sale(root+"/Set/DoubleBulgogiBurger","더블 불고기 버거 세트", R.drawable.doublebulgogiburgerset,15,5500))

        // 필요한 값 계산 및 Setting
        binding.todayBestMenuIcon.setImageResource(CalcInterface(dataSet).getPopular().img)
        binding.todayTotalAccount.text = CalcInterface(dataSet).getTotalAccount() + " 원"

        // 단품 판매 목록
        binding.singleMenu.setOnClickListener {
            dataSet.clear()
            dataSet.add(Sale(root+"/Single/Burger/BigMac","빅맥", R.drawable.bigmacburger,15,4600))
            dataSet.add(Sale(root+"/Single/Burger/QuarterPounderCheese","쿼터파운더 치즈", R.drawable.quarterpoundercheeseburger,15,5200))
            dataSet.add(Sale(root+"/Single/Beverage/CocaCola","코카 콜라", R.drawable.cocacola,15,1800))
            dataSet.add(Sale(root+"/Single/Beverage/Fanta","환타", R.drawable.fanta,15,1800))
            dataSet.add(Sale(root+"/Single/SideMenu/FrenchFries","후렌치 후라이", R.drawable.frenchfries,15,1700))
            dataSet.add(Sale(root+"/Single/SideMenu/McNuggets","맥너겟", R.drawable.mcnuggets,15,1800))
            dataSet.add(Sale(root+"/Single/SideMenu/GoldenMozzarellaCheeseSticks","골든 모짜렐라 치즈 스틱", R.drawable.goldenmozzarellacheesesticks,15,2200))
            (binding.recyclerView.adapter as AccountAdapter).notifyDataSetChanged()
        }
        // 세트 판매 목록
        binding.setMenu.setOnClickListener {
            dataSet.clear()
            dataSet.add(Sale(root+"/Set/BigMac","빅맥 세트", R.drawable.bigmacburgerset,15,5900))
            dataSet.add(Sale(root+"/Set/QuarterPounderCheese","쿼터파운더 치즈 세트", R.drawable.quarterpoundercheeseburgerset,15,6700))
            dataSet.add(Sale(root+"/Set/DoubleBeefMeetChiliBurger","더블 비프 미트칠리 버거 세트", R.drawable.doublebeefmeetchiliburgerset,15,9500))
            dataSet.add(Sale(root+"/Set/DoubleBulgogiBurger","더블 불고기 버거 세트", R.drawable.doublebulgogiburgerset,15,5500))
            (binding.recyclerView.adapter as AccountAdapter).notifyDataSetChanged()
        }

        // RecyclerView 설정
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        binding.recyclerView.adapter = AccountAdapter(dataSet)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.root.context,
                LinearLayoutManager.VERTICAL)
        )

        return binding.root
    }
}