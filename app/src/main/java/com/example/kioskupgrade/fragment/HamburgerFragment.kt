package com.example.kioskupgrade.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kioskupgrade.DTO.Material
import com.example.kioskupgrade.R
import com.example.kioskupgrade.adapter.StockAdapter
import com.example.kioskupgrade.databinding.FragmentHamburgerBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HamburgerFragment: Fragment() {
    lateinit var binding : FragmentHamburgerBinding
    lateinit var database : DatabaseReference
    var dataSet = mutableListOf<Material>()
    var root = "Stock_DB"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHamburgerBinding.inflate(inflater, container, false)

        // Data 생성
        // 단품
//        dataSet.add(Material(root+"/Single/Burger/BigMac","빅맥", R.drawable.bigmacburger,120))
//        dataSet.add(Material(root+"/Single/Burger/DoubleBeefMeetChiliBurger","더블 비프 미트칠리 버거", R.drawable.doublebeefmeetchiliburger,120))
//        dataSet.add(Material(root+"/Single/Burger/DoubleBulgogiBurger","더블 불고기 버거", R.drawable.doublebeefmeetchiliburger,120))
//        dataSet.add(Material(root+"/Single/Burger/QuarterPounderCheese","쿼터파운더 치즈", R.drawable.quarterpoundercheeseburger,120))
        // 세트
//        dataSet.add(Material(root+"/Set/BigMac","빅맥 세트", R.drawable.bigmacburgerset,120))
//        dataSet.add(Material(root+"/Set/DoubleBeefMeetChiliBurger","더블 비프 미트칠리 버거 세트", R.drawable.doublebeefmeetchiliburgerset,120))
//        dataSet.add(Material(root+"/Set/DoubleBulgogiBurger","더블 불고기 버거 세트", R.drawable.doublebulgogiburgerset,120))
//        dataSet.add(Material(root+"/Set/QuarterPounderCheese","쿼터파운더 치즈 세트", R.drawable.quarterpoundercheeseburgerset,120))

        database = Firebase.database.reference.child(root)
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (dataSet.size > 0)
                    dataSet.clear()

                // 단품
                for (item in snapshot.child("Single/Burger").children) {
                    val key = item.key
                    val data = Material()
                    data.setKey(root+"/Single/Burger/"+key)

                    for (i in item.children) {
                        when(i.key) {
                            "img" -> data.setImg(i.value.toString().toInt())
                            "name" -> data.setName(i.value.toString())
                            "num" -> data.setNum(i.value.toString().toInt())
                        }
                    }

                    if (data != null) {
                        dataSet.add(data)
                    }
                }
                // 세트
                for (item in snapshot.child("Set").children) {
                    val key = item.key
                    val data = Material()
                    data.setKey(root+"/Set/"+key)

                    for (i in item.children) {
                        when(i.key) {
                            "img" -> data.setImg(i.value.toString().toInt())
                            "name" -> data.setName(i.value.toString())
                            "num" -> data.setNum(i.value.toString().toInt())
                        }
                    }

                    if (data != null) {
                        dataSet.add(data)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error",error.message)
            }
        })

        // RecyclerView 설정
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        binding.recyclerView.adapter = StockAdapter(dataSet)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.root.context,
                LinearLayoutManager.VERTICAL)
        )

        return binding.root
    }
}