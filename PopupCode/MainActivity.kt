package com.example.realteamproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var popup_btn : Button = findViewById<Button>(R.id.popup_btn)
        popup_btn.setOnClickListener{popup_btn_listener()}
    }

    fun popup_btn_listener(){
        val custom_dialog = CustomDialog(this)
        custom_dialog.show()
    }
}