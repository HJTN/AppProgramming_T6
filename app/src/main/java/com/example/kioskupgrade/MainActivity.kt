package com.example.kioskupgrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kioskupgrade.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kioskmainBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, KioskmainActivity::class.java)
                startActivity(intent)
            }
        })

        binding.stockBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, StockActivity::class.java)
                startActivity(intent)
            }
        })

        binding.accountBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(applicationContext, AccountActivity::class.java)
                startActivity(intent)
            }
        })
    }
}