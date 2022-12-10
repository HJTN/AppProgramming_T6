package com.example.kioskupgrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kioskupgrade.databinding.ActivityMainBinding

//메인화면(어플 실행시 첫 화면) 제어

class MainActivity : AppCompatActivity() {
    var clickNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stockBtn.visibility = View.GONE
        binding.accountBtn.visibility = View.GONE

        binding.logo.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                clickNum++

                if (clickNum  > 9) {
                    binding.stockBtn.visibility = View.VISIBLE
                    binding.accountBtn.visibility = View.VISIBLE
                }
                else {
                    binding.stockBtn.visibility = View.GONE
                    binding.accountBtn.visibility = View.GONE
                }
            }
        })
        binding.kioskmainBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, SubActivity::class.java)
                startActivity(intent)
            }
        })

        binding.kioskmainBtn2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, SubActivity::class.java)
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