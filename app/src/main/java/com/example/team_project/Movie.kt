package com.example.team_project

import java.io.Serializable

class Movie(
    val name: String,  val genre: String, val year: String, val photo: String

// 상속받는 것처럼 구현
) : Serializable {
}