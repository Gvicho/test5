package com.example.test5

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test5.databinding.CourseItemBinding
import com.example.test5.databinding.RecyclerItemBinding


class ActiveNewCoursesRecyclerAdapter() : ListAdapter<CourseData, RecyclerView.ViewHolder>(
    DIFF_CALLBACK
) {
    lateinit var myAdaper: NewCoursesRecyclerAdapter

    companion object {

        const val itemTypeCourse = 1
        const val itemTypeRecycler = 2



        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CourseData>() {
            override fun areItemsTheSame(oldItem: CourseData, newItem: CourseData): Boolean {
                return when {
                    oldItem is CourseData.SingleCourse && newItem is CourseData.SingleCourse -> oldItem.course.id == newItem.course.id
                    oldItem is CourseData.CourseList && newItem is CourseData.CourseList -> oldItem.courses == newItem.courses
                    else -> false
                }
            }

            override fun areContentsTheSame(oldItem: CourseData, newItem: CourseData): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class CourseViewHolder(private val binding: CourseItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(courses: Courses){
            courses.title?.let {
                binding.tvTitle.text = it
            }
        }

    }

    inner class CourseRecyclerViewHolder(private val binding: RecyclerItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(courses: List<Courses>){
            myAdaper = NewCoursesRecyclerAdapter()
            val _layoutManager = LinearLayoutManager(binding.recycler.context, LinearLayoutManager.HORIZONTAL, false)
            binding.recycler.apply {
                adapter = myAdaper
                layoutManager = _layoutManager
            }
            myAdaper.submitList(courses)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CourseData.SingleCourse -> itemTypeCourse
            is CourseData.CourseList -> itemTypeRecycler
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("tag123","onCreate")
        return when (viewType) {
            itemTypeCourse -> CourseViewHolder(CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            itemTypeRecycler -> CourseRecyclerViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalStateException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("tag123","onBind")
        when (val item = getItem(position)) {
            is CourseData.SingleCourse -> if (holder is CourseViewHolder) holder.bind(item.course)
            is CourseData.CourseList -> if (holder is CourseRecyclerViewHolder) holder.bind(item.courses)
        }
    }
}

