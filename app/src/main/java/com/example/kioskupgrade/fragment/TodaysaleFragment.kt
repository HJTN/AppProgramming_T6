package com.example.kioskupgrade.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kioskupgrade.DTO.Sale
import com.example.kioskupgrade.R
import com.example.kioskupgrade.`interface`.CalcInterface
import com.example.kioskupgrade.adapter.AccountAdapter
import com.example.kioskupgrade.databinding.AccountPrintBinding
import com.example.kioskupgrade.databinding.FragmentTodaysaleBinding
import com.google.firebase.database.DatabaseReference

class TodaysaleFragment: Fragment() {
    lateinit var binding : FragmentTodaysaleBinding
    lateinit var database : DatabaseReference
    var dataSet = mutableListOf<Sale>()
    var root = "Account_DB/Today"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodaysaleBinding.inflate(inflater, container, false)

        // Data 가져오기
        // 단품
        dataSet.add(Sale("더블 불고기 버거",R.drawable.burger10,56,4400))
        dataSet.add(Sale("빅맥",R.drawable.burger9,87,4600))
        dataSet.add(Sale("맥치킨",R.drawable.burger6,38,3300))
        dataSet.add(Sale("골든 모짜렐라 치즈스틱",R.drawable.sidemenu8,75,4000))
        // 세트
        dataSet.add(Sale("더블 비프 미트칠리 버거 세트",R.drawable.burgerset2,40,8200))
        dataSet.add(Sale("빅맥 세트",R.drawable.burgerset1,60,5900))
        dataSet.add(Sale("더블 쿼터파운더 치즈 세트",R.drawable.burgerset3,50,8400))
        dataSet.add(Sale("더블 불고기 버거 세트",R.drawable.burgerset4,70,5500))

        // 하단바 텍스트 설정
        binding.infoTitle.text = "총 판매량"
        binding.infoContent.text = CalcInterface(dataSet).getTotalSale() + " 개"

        // 단품 판매 목록
        binding.singleMenu.setOnClickListener {
            dataSet.clear()
            dataSet.add(Sale("더블 불고기 버거",R.drawable.burger10,56,4400))
            dataSet.add(Sale("빅맥",R.drawable.burger9,87,4600))
            dataSet.add(Sale("맥치킨",R.drawable.burger6,38,3300))
            dataSet.add(Sale("골든 모짜렐라 치즈스틱",R.drawable.sidemenu8,75,4000))
            (binding.recyclerView.adapter as AccountAdapter).notifyDataSetChanged()

            // 하단바 텍스트 설정
            binding.infoContent.text = CalcInterface(dataSet).getTotalSale() + " 개"
        }
        // 세트 판매 목록
        binding.setMenu.setOnClickListener {
            dataSet.clear()
            dataSet.add(Sale("더블 비프 미트칠리 버거 세트",R.drawable.burgerset2,40,8200))
            dataSet.add(Sale("빅맥 세트",R.drawable.burgerset1,60,5900))
            dataSet.add(Sale("더블 쿼터파운더 치즈 세트",R.drawable.burgerset3,50,8400))
            dataSet.add(Sale("더블 불고기 버거 세트",R.drawable.burgerset4,70,5500))
            (binding.recyclerView.adapter as AccountAdapter).notifyDataSetChanged()

            // 하단바 텍스트 설정
            binding.infoContent.text = CalcInterface(dataSet).getTotalSale() + " 개"
        }

        // 출력 버튼 설정
        binding.printBtn.setOnClickListener {
            val dialogBinding = AccountPrintBinding.inflate(inflater)
            dialogBinding.totalSale.text = CalcInterface(dataSet).getTotalSale() + " 개"
            dialogBinding.totalAccount.text = CalcInterface(dataSet).getTotalAccount() + " 원"

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
}