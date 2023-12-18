package com.example.test5

import android.os.Bundle

import com.example.test5.databinding.FragmentCoursesBinding
import com.example.test5.databinding.FragmentMessagesBinding


class MessagesFragment : BaseFragment<FragmentMessagesBinding>(FragmentMessagesBinding::inflate) {


    companion object {

        @JvmStatic
        fun newInstance() =
            MessagesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}