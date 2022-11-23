package com.example.kioskupgrade.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kioskupgrade.databinding.FragmentOnedaysaleBinding

class OnedaysaleFragment: Fragment() {
    lateinit var binding : FragmentOnedaysaleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnedaysaleBinding.inflate(inflater, container, false)
        return binding.root
    }
}