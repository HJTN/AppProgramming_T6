package com.example.kioskupgrade

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kioskupgrade.databinding.ActivityOrderBinding
import com.example.kioskupgrade.databinding.ActivityPaymentBinding

//결제화면 제어

class paymentActivity : AppCompatActivity() {

    lateinit var tutorial_renderer : TutorialViewRenderer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Button1: Button = findViewById(R.id.button1)
        Button1.setOnClickListener {
            Toast.makeText(this,"포장을 눌렀습니다",Toast.LENGTH_SHORT).show();

            if(CrossActivityInfo.isTutorial)
                tutorial_renderer.MovePhase()
        }

        val Button2: Button = findViewById(R.id.button2)
        Button2.setOnClickListener {
            Toast.makeText(this,"매장에서 식사를 눌렀습니다",Toast.LENGTH_SHORT).show();

            if(CrossActivityInfo.isTutorial)
                tutorial_renderer.MovePhase()
        }

        val Button3: Button = findViewById(R.id.button3)
        Button3.setOnClickListener {
            Toast.makeText(this,"신용카드로 결제",Toast.LENGTH_SHORT).show();

            if(CrossActivityInfo.isTutorial)
                tutorial_renderer.MovePhase()
        }
        val Button4: Button = findViewById(R.id.button4)
        Button4.setOnClickListener {
            Toast.makeText(this,"현금으로 결제",Toast.LENGTH_SHORT).show();

            if(CrossActivityInfo.isTutorial)
                tutorial_renderer.MovePhase()
        }
        val Button5: Button = findViewById(R.id.button5)
        Button5.setOnClickListener {
            Toast.makeText(this,"토스로 결제",Toast.LENGTH_SHORT).show();

            if(CrossActivityInfo.isTutorial)
                tutorial_renderer.MovePhase()
        }
        val Button6: Button = findViewById(R.id.button6)
        Button6.setOnClickListener {
            Toast.makeText(this,"기타결제수단으로 결제",Toast.LENGTH_SHORT).show();

            if(CrossActivityInfo.isTutorial)
                tutorial_renderer.MovePhase()
        }

        if(CrossActivityInfo.isTutorial){
            SetTutorialView(binding)
        }
    }

    fun SetTutorialView(binding : ActivityPaymentBinding){
        val panels : MutableList<ImageView> = mutableListOf()
        panels.add(binding.tutorialPanel1)
        panels.add(binding.tutorialPanel2)
        panels.add(binding.tutorialPanel3)
        panels.add(binding.tutorialPanel4)

        tutorial_renderer = TutorialViewRenderer(this.applicationContext, panels, binding.tutorialText, binding.tutorialNextButton)
//        tutorial_renderer.Highlight(0.05f, 0.6f, 0.97f, 1f)
//        tutorial_renderer.SetText("",0.01f, 1.35f, 30f)
//        tutorial_renderer.SetNextButton(0.1f, 2f)

        tutorial_renderer.PassPhase_to(3)
        tutorial_renderer.StartTutorial()
    }
}