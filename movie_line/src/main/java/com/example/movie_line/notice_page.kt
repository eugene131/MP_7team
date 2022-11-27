package com.example.movie_line

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_line.databinding.ActivityNoticePageBinding

class notice_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNoticePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Notice")

        val datas = mutableListOf<String>()
        for (i in 1..10) {
            datas.add("item $i")
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = Notice_Adapter(datas)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this,
            LinearLayoutManager.VERTICAL)
        )
    }
}