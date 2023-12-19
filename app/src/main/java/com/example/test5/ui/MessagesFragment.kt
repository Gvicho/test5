package com.example.test5.ui

import android.os.Bundle
import com.example.test5.BaseFragment

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