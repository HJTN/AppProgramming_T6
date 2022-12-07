package com.example.kioskupgrade

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kioskupgrade.databinding.ActivityAccountBinding
import com.example.kioskupgrade.fragment.*
import com.google.android.material.tabs.TabLayout

class AccountActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomBar1.visibility = View.GONE
        binding.bottomBar2.visibility = View.GONE

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
                    "전체" -> {
                        transaction.replace(binding.tabContent.id, AccountmainFragment())
                        binding.bottomBar1.visibility = View.GONE
                        binding.bottomBar2.visibility = View.GONE
                    }
                    "1일 판매량" -> {
                        transaction.replace(binding.tabContent.id, TodaysaleFragment())
                        binding.bottomBar1.visibility = View.VISIBLE
                        binding.bottomBar2.visibility = View.VISIBLE
                        binding.infoTitle.text = "총 판매량"
                        binding.infoContent.text = "xx 개"
                    }
                    "누적 판매량" -> {
                        transaction.replace(binding.tabContent.id, CumulatesaleFragment())
                        binding.bottomBar1.visibility = View.VISIBLE
                        binding.bottomBar2.visibility = View.VISIBLE
                        binding.infoTitle.text = "총 판매량"
                        binding.infoContent.text = "xx 개"
                    }
                    "총 판매 금액" -> {
                        transaction.replace(binding.tabContent.id, TotalamountFragment())
                        binding.bottomBar1.visibility = View.VISIBLE
                        binding.bottomBar2.visibility = View.VISIBLE
                        binding.infoTitle.text = "총 판매 금액"
                        binding.infoContent.text = "xx,xxx,xxx 원"
                    }
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