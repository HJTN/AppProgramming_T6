package com.example.kioskupgrade

import android.app.Fragment
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kioskupgrade.DTO.Material
import com.example.kioskupgrade.databinding.ActivityStockBinding
import com.example.kioskupgrade.databinding.FragmentStockmainBinding
import com.example.kioskupgrade.fragment.BeverageFragment
import com.example.kioskupgrade.fragment.HamburgerFragment
import com.example.kioskupgrade.fragment.StockmainFragment
import com.example.kioskupgrade.fragment.SidemenuFragment
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DatabaseReference

class StockActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var transaction: FragmentTransaction
    lateinit var database: DatabaseReference
    var dataSet = mutableListOf<Material>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val asBinding = ActivityStockBinding.inflate(layoutInflater)
        setContentView(asBinding.root)

        val tabLayout = asBinding.tabs

        val tab1: TabLayout.Tab = tabLayout.newTab()
        tab1.text = "식재료"
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
        transaction.add(asBinding.tabContent.id, StockmainFragment())
        transaction.commit()

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()

                when(tab?.text) {
                    "식재료" -> transaction.replace(asBinding.tabContent.id, StockmainFragment())
                    "버거" -> transaction.replace(asBinding.tabContent.id, HamburgerFragment())
                    "음료" -> transaction.replace(asBinding.tabContent.id, BeverageFragment())
                    "사이드 메뉴" -> transaction.replace(asBinding.tabContent.id, SidemenuFragment())
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