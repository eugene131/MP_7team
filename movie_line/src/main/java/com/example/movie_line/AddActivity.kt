package com.example.movie_line

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
//    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding= ActivityAddBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        // actionbar
        setContentView(R.layout.activity_add)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle("")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}