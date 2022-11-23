package com.example.kioskupgrade.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kioskupgrade.databinding.FragmentAccountmainBinding
import com.example.kioskupgrade.databinding.FragmentStockmainBinding

class AccountmainFragment: Fragment() {
    lateinit var binding : FragmentAccountmainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountmainBinding.inflate(inflater, container, false)
        return binding.root
    }
}