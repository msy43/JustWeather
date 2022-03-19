package com.msy.justweather.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.msy.justweather.databinding.FragmentOnBoardFirstBinding


class OnBoardFirst : Fragment() {

    private var _binding : FragmentOnBoardFirstBinding? = null
    private val onBoardFirstBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentOnBoardFirstBinding.inflate(inflater, container, false)


        return onBoardFirstBinding.root
    }

}