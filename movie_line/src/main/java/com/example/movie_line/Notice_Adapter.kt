package com.example.movie_line

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_line.databinding.NoticeItemMainBinding

class Notice_Adapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class NoticeMyViewHolder(val binding: NoticeItemMainBinding): RecyclerView.ViewHolder(binding.root)
    override fun getItemCount(): Int =datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        NoticeMyViewHolder(NoticeItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as NoticeMyViewHolder).binding
        binding.noticeitemData.text=datas[position]
        binding.noticeItemRoot.setOnClickListener{
            Log.d("test","item root click: $position")
        }
    }
}