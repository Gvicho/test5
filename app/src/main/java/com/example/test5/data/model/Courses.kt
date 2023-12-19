package com.example.test5.data.model

import com.squareup.moshi.Json

data class Courses(@Json(name = "id")
                   val id:Int,
                   @Json(name = "icon_type")
                   val _iconType:String? = "",
                   @Json(name = "duration")
                   val duration:Int? = -1,
                   @Json(name = "title")
                   val title:String = "",
                   @Json(name = "question")
                   val question:String? = "",
                   @Json(name = "main_color")
                   val mainColor:String? = "",
                   @Json(name = "booking_time")
                   val bookingTime:String? = "",
                   @Json(name = "progress")
                   val progress:Int? = -1,
                   @Json(name = "background_color_present")
                   val backgroundColorPresent:Int? = 0,
                   @Json(name = "play_button_color_present")
                   val playButtonColorPresent:String? = "",
                   @Json(name = "image")
                   val image:String? = "") {

    private var iconType: IconType = IconType.EMPTY

    init {
        iconType = when(_iconType){
            "settings" -> IconType.SETTINGS
            "card" -> IconType.CARD
            else -> IconType.EMPTY
        }
    }

    fun getIconType(): IconType {
        return iconType
    }
}

enum class IconType{
    SETTINGS,
    CARD,
    EMPTY
}

sealed class CourseData {
    data class SingleCourse(val course: Courses) : CourseData()
    data class CourseList(val courses: List<Courses>) : CourseData()
}