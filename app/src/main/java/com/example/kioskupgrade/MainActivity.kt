package com.example.kioskupgrade

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.kioskupgrade.databinding.ActivityMainBinding

//메인화면(어플 실행시 첫 화면) 제어

class MainActivity : AppCompatActivity() {
    var clickNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(binding.root)

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

//      val myImage : ImageView = ImageView(applicationContext)
//      myImage.setImageResource(R.drawable.hamburger)
//      binding.mainlayout.addView(myImage)

        val display = this.applicationContext?.resources?.displayMetrics
        val deviceWidth : Int = display?.widthPixels!!
        val deviceHeight : Int = display?.heightPixels!!

        val lx : Float = deviceWidth.times(0.3f); val ly: Float = deviceHeight.times(0.1f)
        val rx : Float = deviceWidth.times(0.7f); val ry: Float = deviceHeight.times(0.4f)

        val panel1 = binding.tutorialPanel1
        val panel2 = binding.tutorialPanel2
        val panel3 = binding.tutorialPanel3
        val panel4 = binding.tutorialPanel4

        panel1.visibility = View.VISIBLE
        SetImagePos(panel1, 0f, 0f, lx.toInt(), ry.toInt())

        panel2.visibility = View.VISIBLE
        SetImagePos(panel2, 0f, ry, rx.toInt(), deviceHeight - ry.toInt())

        panel3.visibility = View.VISIBLE
        SetImagePos(panel3, rx, ly, deviceWidth - lx.toInt(), deviceHeight - ly.toInt())

        panel4.visibility = View.VISIBLE
        SetImagePos(panel4, lx, 0f, deviceWidth - lx.toInt(), ly.toInt())

        setContentView(binding.root)
    }

    fun SetImagePos(img : ImageView, x:Float,y:Float,w:Int,h:Int){
        img.x = x; img.y = y;
        img.getLayoutParams().width = w
        img.getLayoutParams().height = h
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.requestLayout()
    }
}