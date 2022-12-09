package com.example.kioskupgrade

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kioskupgrade.databinding.ActivityOrderBinding
import com.example.kioskupgrade.fragment.BeverageOrderFragment
import com.example.kioskupgrade.fragment.HamburgerOrderFragment
import com.example.kioskupgrade.fragment.SidemenuOrderFragment
import com.example.teamprogect.fragment.RecommendOrderFragment
import com.google.android.material.tabs.TabLayout

//주문 화면 관리

class SubActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabs

        val tab1: TabLayout.Tab = tabLayout.newTab()
        tab1.text = "추천 메뉴"
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
        transaction.add(binding.tabContent.id, RecommendOrderFragment())
        transaction.commit()

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()

                when(tab?.text) {
                    "추천 메뉴" -> transaction.replace(binding.tabContent.id, RecommendOrderFragment())
                    "버거" -> transaction.replace(binding.tabContent.id, HamburgerOrderFragment())
                    "음료" -> transaction.replace(binding.tabContent.id, BeverageOrderFragment())
                    "사이드 메뉴" -> transaction.replace(binding.tabContent.id, SidemenuOrderFragment())
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



        val next1Button: Button = findViewById(R.id.purchase)
        next1Button.setOnClickListener {
            val intent = Intent(applicationContext, paymentActivity::class.java)
            startActivity(intent)
        }
        val next2Button: Button = findViewById(R.id.redo)
        next2Button.setOnClickListener{popupbtnlistener()}


    }
    fun popupbtnlistener(){
        val customdialog = CustomDialog(this)
        customdialog.show()
    }


}

