package com.example.kioskupgrade

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//결제화면 제어

class paymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val Button1: Button = findViewById(R.id.button1)
        Button1.setOnClickListener {
            Toast.makeText(this,"포장을 눌렀습니다",Toast.LENGTH_SHORT).show();
        }

        val Button2: Button = findViewById(R.id.button2)
        Button2.setOnClickListener {
            Toast.makeText(this,"매장에서 식사를 눌렀습니다",Toast.LENGTH_SHORT).show();
        }

        val Button3: Button = findViewById(R.id.button3)
        Button3.setOnClickListener {
            Toast.makeText(this,"신용카드로 결제",Toast.LENGTH_SHORT).show();
        }
        val Button4: Button = findViewById(R.id.button4)
        Button4.setOnClickListener {
            Toast.makeText(this,"현금으로 결제",Toast.LENGTH_SHORT).show();
        }
        val Button5: Button = findViewById(R.id.button5)
        Button5.setOnClickListener {
            Toast.makeText(this,"토스로 결제",Toast.LENGTH_SHORT).show();
        }
        val Button6: Button = findViewById(R.id.button6)
        Button6.setOnClickListener {
            Toast.makeText(this,"기타결제수단으로 결제",Toast.LENGTH_SHORT).show();
        }


    }}