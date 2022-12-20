package com.example.movie_line

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_my_page.*


class MyPageActivity : AppCompatActivity() {
    //lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle("")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //val pref = applicationContext.getSharedPreferences("UserInfo", MODE_PRIVATE)

        //user_nickname.text = pref.getString("Nickname", "")
        //name = pref.getString("Nickname", "").toString()
        //Log.d("test", name)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}