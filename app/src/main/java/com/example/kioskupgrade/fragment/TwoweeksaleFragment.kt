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
import com.example.kioskupgrade.databinding.*
import com.google.firebase.database.DatabaseReference

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
        // 단품
        dataSet.add(Sale("더블 불고기 버거",R.drawable.burger10,109,4400))
        dataSet.add(Sale("코카 콜라",R.drawable.beverage3,98,1800))
        dataSet.add(Sale("빅맥",R.drawable.burger9,65,4600))
        dataSet.add(Sale("쿼터파운더 치즈",R.drawable.burger7,80,5200))
        dataSet.add(Sale("더블 쿼터파운더 치즈",R.drawable.burger11,80,7000))
        dataSet.add(Sale("맥치킨",R.drawable.burger6,80,3300))
        dataSet.add(Sale("에그 불고기 버거",R.drawable.burger4,53,3200))
        // 세트
        dataSet.add(Sale("더블 비프 미트칠리 버거 세트",R.drawable.burgerset2,106,8200))
        dataSet.add(Sale("빅맥 세트",R.drawable.burgerset1,160,5900))
        dataSet.add(Sale("더블 쿼터파운더 치즈 세트",R.drawable.burgerset3,68,8400))
        dataSet.add(Sale("더블 불고기 버거 세트",R.drawable.burgerset4,130,5500))
        dataSet.add(Sale("불고기 버거 세트",R.drawable.burgerset5,110,4300))
        dataSet.add(Sale("베이컨 토마토 디럭스 세트",R.drawable.burgerset6,120,7400))

        // 하단바 텍스트 설정
        binding.infoTitle.text = "총 판매 금액"
        binding.infoContent.text = CalcInterface(dataSet).getTotalAccount() + " 원"

        // 단품 판매 목록
        binding.singleMenu.setOnClickListener {
            dataSet.clear()
            dataSet.add(Sale("더블 불고기 버거",R.drawable.burger10,109,4400))
            dataSet.add(Sale("코카 콜라",R.drawable.beverage3,98,1800))
            dataSet.add(Sale("빅맥",R.drawable.burger9,65,4600))
            dataSet.add(Sale("쿼터파운더 치즈",R.drawable.burger7,80,5200))
            dataSet.add(Sale("더블 쿼터파운더 치즈",R.drawable.burger11,80,7000))
            dataSet.add(Sale("맥치킨",R.drawable.burger6,80,3300))
            dataSet.add(Sale("에그 불고기 버거",R.drawable.burger4,53,3200))
            (binding.recyclerView.adapter as AccountAdapter).notifyDataSetChanged()

            // 하단바 텍스트 설정
            binding.infoContent.text = CalcInterface(dataSet).getTotalAccount() + " 원"
        }
        // 세트 판매 목록
        binding.setMenu.setOnClickListener {
            dataSet.clear()
            dataSet.add(Sale("더블 비프 미트칠리 버거 세트",R.drawable.burgerset2,106,8200))
            dataSet.add(Sale("빅맥 세트",R.drawable.burgerset1,160,5900))
            dataSet.add(Sale("더블 쿼터파운더 치즈 세트",R.drawable.burgerset3,68,8400))
            dataSet.add(Sale("더블 불고기 버거 세트",R.drawable.burgerset4,130,5500))
            dataSet.add(Sale("불고기 버거 세트",R.drawable.burgerset5,110,4300))
            dataSet.add(Sale("베이컨 토마토 디럭스 세트",R.drawable.burgerset6,120,7400))
            (binding.recyclerView.adapter as AccountAdapter).notifyDataSetChanged()

            // 하단바 텍스트 설정
            binding.infoContent.text = CalcInterface(dataSet).getTotalAccount() + " 원"
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