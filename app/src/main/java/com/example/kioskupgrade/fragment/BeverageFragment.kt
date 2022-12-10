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
import com.example.kioskupgrade.databinding.FragmentBeverageBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BeverageFragment: Fragment() {
    lateinit var binding : FragmentBeverageBinding
    lateinit var database : DatabaseReference
    var dataSet = mutableListOf<Material>()
    var root = "Stock_DB/Single/Beverage"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeverageBinding.inflate(inflater, container, false)

        // Data 생성
        // 음료
//        dataSet.add(Material(root+"/CocaCola","코카 콜라", R.drawable.cocacola,120))
//        dataSet.add(Material(root+"/Fanta","환타", R.drawable.fanta,120))
//        dataSet.add(Material(root+"/Sprite","스프라이트", R.drawable.sprite,120))
//        dataSet.add(Material(root+"/Water","생수", R.drawable.water,120))

        database = Firebase.database.reference.child(root)
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (dataSet.size > 0)
                    dataSet.clear()

                // 음료
                for (item in snapshot.children) {
                    val key = item.key
                    val data = Material()
                    data.setKey(root+"/"+key)

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