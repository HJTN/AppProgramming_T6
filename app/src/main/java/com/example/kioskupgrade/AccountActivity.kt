package com.example.kioskupgrade

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kioskupgrade.databinding.ActivityStockBinding
import com.example.kioskupgrade.fragment.*
import com.google.android.material.tabs.TabLayout

class AccountActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabs

        val tab1: TabLayout.Tab = tabLayout.newTab()
        tab1.text = "전체"
        tabLayout.addTab(tab1)

        val tab2: TabLayout.Tab = tabLayout.newTab()
        tab2.text = "1일 판매량"
        tabLayout.addTab(tab2)

        val tab3: TabLayout.Tab = tabLayout.newTab()
        tab3.text = "누적 판매량"
        tabLayout.addTab(tab3)

        val tab4: TabLayout.Tab = tabLayout.newTab()
        tab4.text = "총 판매 금액"
        tabLayout.addTab(tab4)

        fragmentManager = supportFragmentManager
        transaction = fragmentManager.beginTransaction()
        transaction.add(binding.tabContent.id, AccountmainFragment())
        transaction.commit()

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()

                when(tab?.text) {
                    "전체" -> transaction.replace(binding.tabContent.id, AccountmainFragment())
                    "1일 판매량" -> transaction.replace(binding.tabContent.id, OnedaysaleFragment())
                    "누적 판매량" -> transaction.replace(binding.tabContent.id, CumulatesaleFragment())
                    "총 판매 금액" -> transaction.replace(binding.tabContent.id, TotalamountFragment())
                }
                transaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("TabButton", "onTabUnselected...")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("TabButton", "onTabReselected...")
            }
        })
    }
}