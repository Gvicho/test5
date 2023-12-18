package com.example.homework17_leacture20.data.remote

import com.example.homework18.remote.GetCoursesAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {

    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


    val getCoursesAPI : GetCoursesAPI by lazy {
        retrofit.create(GetCoursesAPI::class.java)
    }

}