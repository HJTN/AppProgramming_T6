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
        dataSet.add(Sale("빅맥",R.drawable.burger9,34,4600))
        dataSet.add(Sale("코카 콜라",R.drawable.beverage3,45,1800))
        dataSet.add(Sale("환타",R.drawable.beverage6,23,1800))
        dataSet.add(Sale("더블 불고기 버거",R.drawable.burger10,55,4400))

        // 필요한 값 계산 및 Setting
        var popular = getPopular(dataSet)
        var totalAccount = getTotalAccount(dataSet)
        binding.todayBestMenuIcon.setImageResource(popular.img)
        binding.todayTotalAccount.text = "$totalAccount 원"

        // Todo
        binding.singleMenu.setOnClickListener {
            Toast.makeText(binding.root.context, "단품!", Toast.LENGTH_SHORT).show()
        }
        // Todo
        binding.setMenu.setOnClickListener {
            Toast.makeText(binding.root.context, "세트!", Toast.LENGTH_SHORT).show()
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

    fun getPopular(dataSet: MutableList<Sale>): Sale {
        var target = Sale()
        var mostNum = 0

        for (data in dataSet) {
            if (mostNum < data.num)
                target = data
        }

        return target
    }

    fun getTotalAccount(dataSet: MutableList<Sale>): Int {
        var totalAccount = 0

        for (data in dataSet) {
            totalAccount += data.num * data.account
        }
        return totalAccount
    }
}