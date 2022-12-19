package com.example.team_project

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class test_detail( val context : Context, val name:String, val years:String ) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_test_detail)
        val view: View = LayoutInflater.from(context).inflate(R.layout.activity_test_detail, null)

        val movieName = view.findViewById<TextView>(R.id.movie_name)
        val movieYear = view.findViewById<TextView>(R.id.movie_year)

        /* ArrayList<Dog>의 변수 dog의 이미지와 데이터를 ImageView와 TextView에 담는다. */
        movieName.text = name
        movieYear.text = years

    }
}