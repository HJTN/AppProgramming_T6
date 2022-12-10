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
    var root = "Stock_DB"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodaysaleBinding.inflate(inflater, container, false)

        // Data 가져오기
        // 단품
        dataSet.add(Sale(root+"/Single/Burger/BigMac","빅맥", R.drawable.bigmacburger,30,4600))
        dataSet.add(Sale(root+"/Single/Burger/QuarterPounderCheese","쿼터파운더 치즈", R.drawable.quarterpoundercheeseburger,30,5200))
        dataSet.add(Sale(root+"/Single/Beverage/CocaCola","코카 콜라", R.drawable.cocacola,30,1800))
        dataSet.add(Sale(root+"/Single/Beverage/Fanta","환타", R.drawable.fanta,30,1800))
        dataSet.add(Sale(root+"/Single/SideMenu/FrenchFries","후렌치 후라이", R.drawable.frenchfries,30,1700))
        dataSet.add(Sale(root+"/Single/SideMenu/McNuggets","맥너겟", R.drawable.mcnuggets,30,1800))
        dataSet.add(Sale(root+"/Single/SideMenu/GoldenMozzarellaCheeseSticks","골든 모짜렐라 치즈 스틱", R.drawable.goldenmozzarellacheesesticks,30,2200))
        // 세트
        dataSet.add(Sale(root+"/Set/BigMac","빅맥 세트", R.drawable.bigmacburgerset,30,5900))
        dataSet.add(Sale(root+"/Set/QuarterPounderCheese","쿼터파운더 치즈 세트", R.drawable.quarterpoundercheeseburgerset,30,6700))
        dataSet.add(Sale(root+"/Set/DoubleBeefMeetChiliBurger","더블 비프 미트칠리 버거 세트", R.drawable.doublebeefmeetchiliburgerset,30,9500))
        dataSet.add(Sale(root+"/Set/DoubleBulgogiBurger","더블 불고기 버거 세트", R.drawable.doublebulgogiburgerset,30,5500))

        // 하단바 텍스트 설정
        binding.infoTitle.text = "총 판매량"
        binding.infoContent.text = CalcInterface(dataSet).getTotalSale() + " 개"

        // 단품 판매 목록
        binding.singleMenu.setOnClickListener {
            dataSet.clear()
            dataSet.add(Sale(root+"/Single/Burger/BigMac","빅맥", R.drawable.bigmacburger,30,4600))
            dataSet.add(Sale(root+"/Single/Burger/QuarterPounderCheese","쿼터파운더 치즈", R.drawable.quarterpoundercheeseburger,30,5200))
            dataSet.add(Sale(root+"/Single/Beverage/CocaCola","코카 콜라", R.drawable.cocacola,30,1800))
            dataSet.add(Sale(root+"/Single/Beverage/Fanta","환타", R.drawable.fanta,30,1800))
            dataSet.add(Sale(root+"/Single/SideMenu/FrenchFries","후렌치 후라이", R.drawable.frenchfries,30,1700))
            dataSet.add(Sale(root+"/Single/SideMenu/McNuggets","맥너겟", R.drawable.mcnuggets,30,1800))
            dataSet.add(Sale(root+"/Single/SideMenu/GoldenMozzarellaCheeseSticks","골든 모짜렐라 치즈 스틱", R.drawable.goldenmozzarellacheesesticks,15,2200))
            (binding.recyclerView.adapter as AccountAdapter).notifyDataSetChanged()

            // 하단바 텍스트 설정
            binding.infoContent.text = CalcInterface(dataSet).getTotalSale() + " 개"
        }
        // 세트 판매 목록
        binding.setMenu.setOnClickListener {
            dataSet.clear()
            dataSet.add(Sale(root+"/Set/BigMac","빅맥 세트", R.drawable.bigmacburgerset,30,5900))
            dataSet.add(Sale(root+"/Set/QuarterPounderCheese","쿼터파운더 치즈 세트", R.drawable.quarterpoundercheeseburgerset,30,6700))
            dataSet.add(Sale(root+"/Set/DoubleBeefMeetChiliBurger","더블 비프 미트칠리 버거 세트", R.drawable.doublebeefmeetchiliburgerset,30,9500))
            dataSet.add(Sale(root+"/Set/DoubleBulgogiBurger","더블 불고기 버거 세트", R.drawable.doublebulgogiburgerset,30,5500))
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