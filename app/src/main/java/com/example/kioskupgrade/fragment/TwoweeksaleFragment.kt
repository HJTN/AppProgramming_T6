package com.example.kioskupgrade.fragment

import android.app.AlertDialog
import android.content.DialogInterface
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
import com.example.kioskupgrade.adapter.PrintAdapter
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

        // Data 가져오기
        dataSet.add(Sale("더블 불고기 버거",R.drawable.burger10,109,4400))
        dataSet.add(Sale("코카 콜라",R.drawable.beverage3,98,1800))
        dataSet.add(Sale("빅맥",R.drawable.burger9,65,4600))
        dataSet.add(Sale("쿼터파운더 치즈",R.drawable.burger7,80,5200))
        dataSet.add(Sale("더블 쿼터파운더 치즈",R.drawable.burger11,80,7000))
        dataSet.add(Sale("맥치킨",R.drawable.burger6,80,3300))
        dataSet.add(Sale("에그 불고기 버거",R.drawable.burger4,53,3200))

        // Todo
        binding.singleMenu.setOnClickListener {
            Toast.makeText(binding.root.context, "단품!", Toast.LENGTH_SHORT).show()
        }
        // Todo
        binding.setMenu.setOnClickListener {
            Toast.makeText(binding.root.context, "세트!", Toast.LENGTH_SHORT).show()
        }

        binding.infoContent.text = getTotalNum(dataSet).toString()

        binding.printBtn.setOnClickListener {
            val dialogBinding = AccountPrintBinding.inflate(inflater)

            dialogBinding.recyclerView.layoutManager = LinearLayoutManager(dialogBinding.root.context)
            dialogBinding.recyclerView.adapter = PrintAdapter(dataSet)

            AlertDialog.Builder(binding.root.context).run {
                setView(dialogBinding.root)
                setPositiveButton("출력", DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(binding.root.context, "출력하였습니다!", Toast.LENGTH_SHORT).show()
                })
                setNegativeButton("취소", null)
                show()
            }
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

    fun getTotalNum(dataSet: MutableList<Sale>): Int {
        var totalNum = 0

        for (data in dataSet) {
            totalNum += data.num
        }
        return totalNum
    }
}