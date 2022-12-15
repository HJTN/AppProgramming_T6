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
import com.example.kioskupgrade.DTO.Sale
import com.example.kioskupgrade.R
import com.example.kioskupgrade.adapter.StockAdapter
import com.example.kioskupgrade.databinding.FragmentStockmainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class StockmainFragment: Fragment() {
    lateinit var binding : FragmentStockmainBinding
    lateinit var database : DatabaseReference
    var dataSet = mutableListOf<Material>()
    var root = "Stock_DB/Material"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStockmainBinding.inflate(inflater, container, false)

        // Data 생성
        // 식재료
//        dataSet.add(Material(root+"/BeefPatty","쇠고기 패티",R.drawable.beefpatty,120))
//        dataSet.add(Material(root+"/BurgerBread","햄버거 빵",R.drawable.burgerbread,120))
//        dataSet.add(Material(root+"/Cheese","치즈",R.drawable.cheese,120))
//        dataSet.add(Material(root+"/ChickenPatty","치킨 패티",R.drawable.chickenpatty,120))
//        dataSet.add(Material(root+"/Egg","달걀",R.drawable.egg,120))
//        dataSet.add(Material(root+"/Lettuce","양상추",R.drawable.lettuce,120))
//        dataSet.add(Material(root+"/Onion","양파",R.drawable.onion,120))
//        dataSet.add(Material(root+"/Tomato","토마토",R.drawable.tomato,120))

        database = Firebase.database.reference.child(root)
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (dataSet.size > 0)
                    dataSet.clear()

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
        Log.d("Data Check", dataSet.size.toString())
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