package com.example.realteamproject

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var applyButton : Button
    private lateinit var cancelButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_custom)

        applyButton = findViewById(R.id.apply_btn)
        cancelButton = findViewById(R.id.cancel_btn)

        // 추가 버튼 클릭 시 onAddButtonClicked 호출 후 종료
        applyButton.setOnClickListener{
            dismiss()
        }

        // 취소 버튼 클릭 시 onCancelButtonClicked 호출 후 종료
        cancelButton.setOnClickListener {
            dismiss()
        }
    }
}