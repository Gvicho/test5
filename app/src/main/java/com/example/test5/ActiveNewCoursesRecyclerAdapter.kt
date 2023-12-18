package com.example.test5

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
            // Set the image
            Glide.with(itemView.context)
                .load(courses.image)
                .into(binding.imageViewIcon)

            // Set the title and booking time
            binding.textViewTitle.text = courses.title
            binding.textViewBookingTime.text = "Booked for ${courses.bookingTime}"

            // Set the progress on the progress bar
            binding.circularProgressBar.progress = courses.progress ?: 0

            // Parse the main background color
            val backgroundColor = courses.mainColor?.let { parseColor(it) } ?: Color.RED // Default color if parsing fails

            // Convert the opacity from 0-100 to 0-255 scale
            val backgroundAlpha = (courses.backgroundColorPresent?.let { it/100.0 * 255.0 } ?: 255).toInt().coerceIn(0, 255)

            // Apply the background color and opacity
            val backgroundDrawable = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 35f // Replace with your actual radius
                setColor(backgroundColor)
                alpha = backgroundAlpha // Set the alpha/opacity
            }
            binding.root.background = backgroundDrawable

            // Safely parse the play button color and set it
            courses.playButtonColorPresent?.let { colorString ->
                val buttonColor = parseColor(colorString)
                val playButtonDrawable = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    setColor(buttonColor)
                }
                binding.playButton.background = playButtonDrawable
            }
        }

        private fun parseColor(colorString: String): Int {
            val formattedColorString = if (!colorString.startsWith("#")) "#$colorString" else colorString
            return try {
                Color.parseColor(formattedColorString)
            } catch (e: IllegalArgumentException) {
                Log.e("Adapter", "Invalid color string: $formattedColorString", e)
                Color.RED // Fallback color in case of error
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

