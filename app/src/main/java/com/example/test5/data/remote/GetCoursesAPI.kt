package com.example.homework18.remote


import com.example.test5.data.model.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET

interface GetCoursesAPI {

    @GET("v3/83160a49-fe85-46ba-bcf8-3cf5aa09f92b")
    suspend fun getCourses(): Response<CoursesResponse>
}