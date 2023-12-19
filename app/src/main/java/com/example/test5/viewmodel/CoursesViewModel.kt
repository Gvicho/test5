package com.example.test5.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework17_leacture20.data.remote.Network
import com.example.homework17_leacture20.data.remote.ResultWrapper
import com.example.test5.data.model.CoursesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoursesViewModel : ViewModel() {
    private val _courses = MutableStateFlow<ResultWrapper<CoursesResponse>?>(null)
    val courses: StateFlow<ResultWrapper<CoursesResponse>?> = _courses

    init {
        fetchCourses()
    }

    private fun fetchCourses() {
        viewModelScope.launch {
            try {
                val response = Network.getCoursesAPI.getCourses()
                if (response.isSuccessful) {
                    _courses.value = ResultWrapper.Success(response.body()!!)
                } else {
                    Log.d("tag123","unsuccessfully")
                    _courses.value = ResultWrapper.Error(response.message())
                }
            } catch (e: Exception) {
                Log.d("tag123","error")
                _courses.value = ResultWrapper.Error(e.toString())
            }
        }
    }

}