package com.example.test5.ui

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework17_leacture20.data.remote.ResultWrapper
import com.example.test5.BaseFragment
import com.example.test5.adapters.ActiveNewCoursesRecyclerAdapter
import com.example.test5.data.model.CourseData
import com.example.test5.databinding.FragmentCoursesBinding
import com.example.test5.viewmodel.CoursesViewModel
import kotlinx.coroutines.launch


class CoursesFragment : BaseFragment<FragmentCoursesBinding>(FragmentCoursesBinding::inflate)  {

    private val viewModel : CoursesViewModel by viewModels()
    private lateinit var adapter: ActiveNewCoursesRecyclerAdapter

    override fun setUp() {
        adapter = ActiveNewCoursesRecyclerAdapter()

        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.courses.collect { resultWrapper ->
                    if( resultWrapper is ResultWrapper.Success){
                        val courseData = resultWrapper.data
                        val list: MutableList<CourseData> = courseData?.newCourses?.let { mutableListOf(
                            CourseData.CourseList(it)
                        ) } ?: mutableListOf()
                        courseData?.let{
                            it.activeCourses.forEach{course ->
                                list.add(CourseData.SingleCourse(course))
                            }
                        }
                        adapter.submitList(list)
                    }else{
                        resultWrapper?.let {
                            Toast.makeText(context, "${resultWrapper.errorMessage}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CoursesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}