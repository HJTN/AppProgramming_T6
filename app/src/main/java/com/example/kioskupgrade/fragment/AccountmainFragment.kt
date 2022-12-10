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
    var root = "Account_DB/Main"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountmainBinding.inflate(inflater, container, false)

        // Data 가져오기
        // 단품
        dataSet.add(Sale("빅맥",R.drawable.burger9,34,4600))
        dataSet.add(Sale("코카 콜라",R.drawable.beverage3,45,1800))
        dataSet.add(Sale("환타",R.drawable.beverage6,23,1800))
        dataSet.add(Sale("더블 불고기 버거",R.drawable.burger10,55,4400))
        dataSet.add(Sale("맥치킨",R.drawable.burger6,38,3300))
        dataSet.add(Sale("골든 모짜렐라 치즈스틱",R.drawable.sidemenu8,75,4000))
        // 세트
        dataSet.add(Sale("더블 비프 미트칠리 버거 세트",R.drawable.burgerset2,106,8200))
        dataSet.add(Sale("빅맥 세트",R.drawable.burgerset1,160,5900))
        dataSet.add(Sale("더블 쿼터파운더 치즈 세트",R.drawable.burgerset3,68,8400))
        dataSet.add(Sale("더블 불고기 버거 세트",R.drawable.burgerset4,130,5500))
        dataSet.add(Sale("불고기 버거 세트",R.drawable.burgerset5,110,4300))
        dataSet.add(Sale("베이컨 토마토 디럭스 세트",R.drawable.burgerset6,120,7400))

        // 필요한 값 계산 및 Setting
        binding.todayBestMenuIcon.setImageResource(CalcInterface(dataSet).getPopular().img)
        binding.todayTotalAccount.text = CalcInterface(dataSet).getTotalAccount() + " 원"

        // 단품 판매 목록
        binding.singleMenu.setOnClickListener {
            dataSet.clear()
            dataSet.add(Sale("빅맥",R.drawable.burger9,34,4600))
            dataSet.add(Sale("코카 콜라",R.drawable.beverage3,45,1800))
            dataSet.add(Sale("환타",R.drawable.beverage6,23,1800))
            dataSet.add(Sale("더블 불고기 버거",R.drawable.burger10,55,4400))
            dataSet.add(Sale("맥치킨",R.drawable.burger6,38,3300))
            dataSet.add(Sale("골든 모짜렐라 치즈스틱",R.drawable.sidemenu8,75,4000))
            (binding.recyclerView.adapter as AccountAdapter).notifyDataSetChanged()
        }
        // 세트 판매 목록
        binding.setMenu.setOnClickListener {
            dataSet.clear()
            dataSet.add(Sale("더블 비프 미트칠리 버거 세트",R.drawable.burgerset2,106,8200))
            dataSet.add(Sale("빅맥 세트",R.drawable.burgerset1,160,5900))
            dataSet.add(Sale("더블 쿼터파운더 치즈 세트",R.drawable.burgerset3,68,8400))
            dataSet.add(Sale("더블 불고기 버거 세트",R.drawable.burgerset4,130,5500))
            dataSet.add(Sale("불고기 버거 세트",R.drawable.burgerset5,110,4300))
            dataSet.add(Sale("베이컨 토마토 디럭스 세트",R.drawable.burgerset6,120,7400))
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