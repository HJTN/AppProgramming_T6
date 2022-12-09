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
        database = Firebase.database.reference.child(root)
        database.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (dataSet.size > 0)
                    dataSet.clear()

                for (item in snapshot.children) {
                    val key = item.key
                    var data = Sale()
                    for (sub in item.children) {
                        when(sub.key) {
                            "img" -> data.setImg(R.drawable.burger)
                            "name" -> data.setName(sub.value.toString())
                            "num" -> data.setNum(sub.value.toString().toInt())
                            "account" -> data.setAccount(sub.value.toString().toInt())
                        }
                    }
//                    val data = item.getValue(Sale::class.java)
                    if (data != null) {
                        dataSet.add(data)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error",error.message)
            }
        })

        // 필요한 값 계산
        var popular = getPopular(dataSet)
        var totalAccount = getTotalAccount(dataSet)


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