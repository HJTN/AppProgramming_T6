package com.example.kioskupgrade

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

//결제화면 제어

class paymentActivity : AppCompatActivity() {

    var items2: ArrayList<String>? = null
    var adapter: ArrayAdapter<String>? = null
    var listView: ListView? = null
    lateinit var priceText: TextView
    lateinit var sumprice: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val intent = intent
        items2 = intent.getStringArrayListExtra("data")
        sumprice= intent.getStringExtra("price").toString()
        priceText = findViewById(R.id.textview4)
        priceText.text =sumprice+"원"

        adapter = ArrayAdapter<String>(
            this@paymentActivity,
            android.R.layout.simple_list_item_1, items2!!
        )

        // 어댑터 설정
        listView = findViewById(R.id.listView2) as ListView?
        listView!!.adapter = adapter

        val Button1: Button = findViewById(R.id.button1)
        Button1.setOnClickListener {
            Toast.makeText(this,"포장을 눌렀습니다",Toast.LENGTH_SHORT).show();
        }

        val Button2: Button = findViewById(R.id.button2)
        Button2.setOnClickListener {
            Toast.makeText(this,"매장에서 식사를 눌렀습니다",Toast.LENGTH_SHORT).show();
        }

        //하단 4개의 결제버튼을 눌렀을시에 발생하는 이벤트의 헨들러

        val eventHandler =	object	:	DialogInterface.OnClickListener {
            override	fun	onClick(dialog:	DialogInterface?, which:	Int)	{

                //긍정 버튼을 누른다면(결제완료)-> finsh activty 이동->어플 종료후 다시 실행
                if(which	==	DialogInterface.BUTTON_POSITIVE)	{
                    val intent = Intent(applicationContext, FinishActivity::class.java)
                    startActivity(intent)
                    //부정 버튼을 누른다면-> ALERT DIALOG DISSMISS
                }else	if(which	==	DialogInterface.BUTTON_NEGATIVE)	{
                    Log.d("ALERTDLG",	"결제를 취소하였습니다 다시 시도해주세요")
                }
            }
        }

        val Button3: Button = findViewById(R.id.button3)
        Button3.setOnClickListener {
            Toast.makeText(this,"신용카드로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_credit, null)

            builder.setView(dialoglayout)
            builder.show()

        }
        val Button4: Button = findViewById(R.id.button4)
        Button4.setOnClickListener {
            Toast.makeText(this,"현금으로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_cash, null)

            builder.setView(dialoglayout)
            builder.show()
        }
        val Button5: Button = findViewById(R.id.button5)
        Button5.setOnClickListener {
            Toast.makeText(this,"토스로 결제",Toast.LENGTH_SHORT).show();
            val builder = AlertDialog.Builder(this@paymentActivity)

            builder.setPositiveButton("결제완료",eventHandler)
            builder.setNegativeButton("취소", eventHandler)

            val inflater = layoutInflater
            val dialoglayout: View = inflater.inflate(R.layout.dialog_toss, null)

            builder.setView(dialoglayout)
            builder.show()

        }
        val Button6: Button = findViewById(R.id.button6)
        Button6.setOnClickListener {
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