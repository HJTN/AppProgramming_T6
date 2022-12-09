package com.example.kioskupgrade


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class FinishActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        Handler().postDelayed(Runnable {
            finishAffinity()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            System.exit(0)
        }, 3000) // 3000==3초

    }
}