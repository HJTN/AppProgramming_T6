package com.example.kioskupgrade

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.kioskupgrade.DTO.Item
import com.example.kioskupgrade.databinding.ActivityOrderBinding
import com.example.kioskupgrade.databinding.MenuItemBinding
import com.example.kioskupgrade.fragment.BeverageOrderFragment
import com.example.kioskupgrade.fragment.HamburgerOrderFragment
import com.example.kioskupgrade.fragment.SidemenuOrderFragment
import com.example.teamprogect.fragment.RecommendOrderFragment
import com.google.android.material.tabs.TabLayout

//주문 화면 관리
var items = ArrayList<String>()
var adapter: ArrayAdapter<String>? = null
var totalPrice : Int = 0
lateinit var priceText: TextView

class SubActivity : AppCompatActivity() {
    lateinit var fragmentManager: FragmentManager
    lateinit var transaction: FragmentTransaction
    var listView: ListView? = null

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

        items.clear()
        totalPrice = 0

        priceText = findViewById(R.id.sum)

        // 어댑터 생성
        adapter = ArrayAdapter<String>(
            this@SubActivity,
            android.R.layout.simple_list_item_1, items!!
        )

        // 어댑터 설정
        listView = findViewById(R.id.listView) as ListView?
        listView!!.adapter = adapter

        val next1Button: Button = findViewById(R.id.purchase)
        next1Button.setOnClickListener {
            val intent = Intent(applicationContext, paymentActivity::class.java)

            // 다음액티비티인 payment activity에 어레이리스트 전달
            intent.putStringArrayListExtra("data",items)
            intent.putExtra("price",totalPrice.toString())
            startActivity(intent)
        }
        val next2Button: Button = findViewById(R.id.redo)
        next2Button.setOnClickListener{
            popupbtnlistener()
        }
    }
    fun popupbtnlistener(){
        val customdialog = CustomDialog(this)
        customdialog.show()
    }


}

class MenuViewHolder(val binding: MenuItemBinding): RecyclerView.ViewHolder(binding.root)

class MenuAdapter(val dataSet: MutableList<Item>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MenuViewHolder(MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("RecyclerView", "onBindViewHolder(): $position")
        val binding = (holder as MenuViewHolder).binding

        binding.itemName.text = dataSet[position].name
        binding.itemPrice.text = dataSet[position].price
        binding.itemImg.setImageResource(dataSet[position].img)

        binding.itemButton.setOnClickListener(View.OnClickListener {
            items.add(binding.itemName.text.toString())
            adapter?.notifyDataSetChanged();
            totalPrice = totalPrice + (binding.itemPrice.text as String).split(" ")[0].toInt()
            priceText.text = "가격: $totalPrice 원"
            Toast.makeText(binding.root.context, "${binding.itemName.text} Clicked", Toast.LENGTH_SHORT).show()
        })
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
