package com.example.test5.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test5.R
import com.example.test5.data.model.Courses
import com.example.test5.data.model.IconType
import com.example.test5.databinding.CourseItem2Binding

class NewCoursesRecyclerAdapter : ListAdapter<Courses, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Courses>() {
            override fun areItemsTheSame(oldItem: Courses, newItem: Courses): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Courses, newItem: Courses): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class CourseViewHolder(private val binding: CourseItem2Binding):RecyclerView.ViewHolder(binding.root){

        fun bind(courses: Courses){

            binding.apply {

                courses.title?.let {
                    tvTitle.text = it
                }
                val icType = courses.getIconType()

                when(icType){
                    IconType.CARD -> binding.appCompatImageView.setImageResource(R.drawable.card)
                    else -> binding.appCompatImageView.setImageResource(R.drawable.settings)
                }

                courses.question?.let {
                    tvQuestion.text = it
                }

                courses.duration?.let {
                    tvTime.text = it.toString()
                }

                val colorString = courses.mainColor
                colorString?.let {
                    try {
                        val color = Color.parseColor("#$colorString")
                        // Create a GradientDrawable with rounded corners
                        val drawable = GradientDrawable().apply {
                            shape = GradientDrawable.RECTANGLE
                            cornerRadius = 65f
                            setColor(color)
                        }


                        binding.root.background = drawable
                    } catch (e: IllegalArgumentException) {
                        // Handle the situation where the color string is not valid (optional)
                    }
                }

            }

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseViewHolder(
            CourseItem2Binding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val animal = getItem(position)
        if(holder is CourseViewHolder)holder.bind(animal)
    }
}
