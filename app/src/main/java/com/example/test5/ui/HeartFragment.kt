package com.example.test5.ui

import android.os.Bundle
import com.example.test5.BaseFragment
import com.example.test5.databinding.FragmentHeartBinding


class HeartFragment : BaseFragment<FragmentHeartBinding>(FragmentHeartBinding::inflate) {


    companion object {

        @JvmStatic
        fun newInstance() =
            HeartFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}