package com.msy.justweather.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.msy.justweather.databinding.FragmentOnBoardFourthBinding

class OnBoardFourth : Fragment() {

    private var _binding : FragmentOnBoardFourthBinding? = null
    private val onBoardFourthBinding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentOnBoardFourthBinding.inflate(inflater, container, false)

        return onBoardFourthBinding.root
    }

}