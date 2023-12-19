package com.example.test5.data.model

import com.squareup.moshi.Json

data class CoursesResponse(
    @Json(name = "new_courses")
    val newCourses: List<Courses>,

    @Json(name = "active_courses")
    val activeCourses: List<Courses>
)