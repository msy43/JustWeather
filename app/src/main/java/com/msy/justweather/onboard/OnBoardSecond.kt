package com.msy.justweather.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.msy.justweather.databinding.FragmentOnBoardSecondBinding

class OnBoardSecond : Fragment() {

    private var _binding : FragmentOnBoardSecondBinding? = null
    private val onBoardSecondBinding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentOnBoardSecondBinding.inflate(inflater, container, false)

        return onBoardSecondBinding.root
    }

}