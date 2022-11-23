package com.example.kioskupgrade.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kioskupgrade.databinding.FragmentSidemenuBinding

class SidemenuFragment: Fragment() {
    lateinit var binding : FragmentSidemenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSidemenuBinding.inflate(inflater, container, false)
        return binding.root
    }
}