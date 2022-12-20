package com.example.movie_line

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties


@IgnoreExtraProperties
data class MovieInfo(
    var movie_name: String? = "",
    var movie_line: String? = "",
    val year : Int? = 0,
    val imageName : String? = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "movie_name" to movie_name,
            "movie_line" to movie_line,
            "year" to year,
            "imageName" to imageName
        )
    }
}