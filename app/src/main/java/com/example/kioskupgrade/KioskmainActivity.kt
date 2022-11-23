package com.example.kioskupgrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kioskupgrade.databinding.ActivityKioskmainBinding
import com.example.kioskupgrade.databinding.ActivityMainBinding

class KioskmainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityKioskmainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}