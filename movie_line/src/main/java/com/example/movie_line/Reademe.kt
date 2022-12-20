package com.example.movie_line

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Reademe : AppCompatActivity() {
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reademe)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle("")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}