package com.example.movie_line

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_my_page.*


class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        val settings = getSharedPreferences("UserInfo", 0)
        user_nickname.text = settings.getString("Nickname", "").toString()
    }
}