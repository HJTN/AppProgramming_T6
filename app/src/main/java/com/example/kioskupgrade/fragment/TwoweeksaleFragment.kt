package com.example.kioskupgrade.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kioskupgrade.DTO.Sale
import com.example.kioskupgrade.R
import com.example.kioskupgrade.adapter.AccountAdapter
import com.example.kioskupgrade.databinding.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TwoweeksaleFragment: Fragment() {
    lateinit var binding : FragmentTwoweeksaleBinding
    lateinit var database : DatabaseReference
    var dataSet = mutableListOf<Sale>()
    var root = "Account_DB/Two-week"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoweeksaleBinding.inflate(inflater, container, false)

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

                    if (data != null) {
                        dataSet.add(data)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error",error.message)
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        binding.recyclerView.adapter = AccountAdapter(dataSet)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(binding.root.context,
                LinearLayoutManager.VERTICAL)
        )

        return binding.root
    }
}