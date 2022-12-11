package com.example.kioskupgrade

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kioskupgrade.databinding.ActivityOrderBinding
import com.example.kioskupgrade.databinding.ActivityPaymentBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.DecimalFormat

//결제화면 제어

class paymentActivity : AppCompatActivity() {
    lateinit var database : DatabaseReference
    val decimalFormat = DecimalFormat("#,###")
    var adapter: ArrayAdapter<String>? = null
    var items: ArrayList<String>? = null
    var itemKeys: ArrayList<String>? = null
    var currentNum = mutableMapOf<String, Int>()
    var sumprice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        items = intent.getStringArrayListExtra("items")
        sumprice= intent.getIntExtra("price", 0)
        binding.totalPrice.text = decimalFormat.format(sumprice)+" 원"

        itemKeys = intent.getStringArrayListExtra("keys")
        database = Firebase.database.reference
        for (k in itemKeys!!) {
            database.child(k).addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    currentNum.put(k, snapshot.child("num").value.toString().toInt()-1)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Error",error.message)
                }
            })
        }

        adapter = ArrayAdapter<String>(
            this@paymentActivity,
            android.R.layout.simple_list_item_1, items!!
        )

        // 어댑터 설정
        binding.listView.adapter = adapter

        binding.packing.setOnClickListener {
            Toast.makeText(this,"포장을 눌렀습니다",Toast.LENGTH_SHORT).show();
        }

        binding.inShop.setOnClickListener {
            Toast.makeText(this,"매장에서 식사를 눌렀습니다",Toast.LENGTH_SHORT).show();
        }

        //하단 4개의 결제버튼을 눌렀을시에 발생하는 이벤트의 헨들러

        val eventHandler =	object	:	DialogInterface.OnClickListener {
            override	fun	onClick(dialog:	DialogInterface?, which:	Int)	{
                //긍정 버튼을 누른다면(결제완료)-> finsh activty 이동->어플 종료후 다시 실행
                if(which	==	DialogInterface.BUTTON_POSITIVE)	{
//                    Log.d("Data Check",currentNum.toString())
                    for (i in currentNum) {
                        var updateData = mutableMapOf<String, Any>()
                        updateData.put("num", i.value)
//                        Log.d("Data Check",updateData.toString())
                        Firebase.database.reference.child(i.key).updateChildren(updateData)
                    }

                    val intent = Intent(applicationContext, FinishActivity::class.java)
                    startActivity(intent)
                    //부정 버튼을 누른다면-> ALERT DIALOG DISSMISS
                }else	if(which	==	DialogInterface.BUTTON_NEGATIVE)	{
                    Log.d("ALERTDLG",	"결제를 취소하였습니다 다시 시도해주세요")
                }
            }
        }

        binding.creditCard.setOnClickListener {
            Toast.makeText(this,"신용카드로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_credit, null)

            builder.setView(dialoglayout)
            builder.show()

        }

        binding.cash.setOnClickListener {
            Toast.makeText(this,"현금으로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_cash, null)

            builder.setView(dialoglayout)
            builder.show()
        }

        binding.toss.setOnClickListener {
            Toast.makeText(this,"토스로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_toss, null)

            builder.setView(dialoglayout)
            builder.show()

        }

        binding.etc.setOnClickListener {
            Toast.makeText(this,"기타결제수단으로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_etc, null)

            builder.setView(dialoglayout)
            builder.show()

        }
    }}
