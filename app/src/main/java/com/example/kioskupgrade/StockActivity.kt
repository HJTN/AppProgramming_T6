package com.example.kioskupgrade

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kioskupgrade.databinding.ActivityStockBinding
import com.example.kioskupgrade.fragment.BeverageFragment
import com.example.kioskupgrade.fragment.HamburgerFragment
import com.example.kioskupgrade.fragment.StockmainFragment
import com.example.kioskupgrade.fragment.SidemenuFragment
import com.google.android.material.tabs.TabLayout

//재고확인 화면 관리

class StockActivity : AppCompatActivity() {
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
        tab2.text = "버거"
        tabLayout.addTab(tab2)

        val tab3: TabLayout.Tab = tabLayout.newTab()
        tab3.text = "음료"
        tabLayout.addTab(tab3)

        val tab4: TabLayout.Tab = tabLayout.newTab()
        tab4.text = "사이드 메뉴"
        tabLayout.addTab(tab4)

        fragmentManager = supportFragmentManager
        transaction = fragmentManager.beginTransaction()
        transaction.add(binding.tabContent.id, StockmainFragment())
        transaction.commit()

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()

                when(tab?.text) {
                    "전체" -> transaction.replace(binding.tabContent.id, StockmainFragment())
                    "버거" -> transaction.replace(binding.tabContent.id, HamburgerFragment())
                    "음료" -> transaction.replace(binding.tabContent.id, BeverageFragment())
                    "사이드 메뉴" -> transaction.replace(binding.tabContent.id, SidemenuFragment())
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