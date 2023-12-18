package com.example.test5

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.test5.databinding.FragmentCoursesBinding
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