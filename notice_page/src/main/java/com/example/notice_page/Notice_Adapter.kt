package com.example.notice_page

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.notice_page.databinding.NoticeItemMainBinding


//noticetime 설정해야함
class Notice_Adapter(val datas: MutableList<String>, val times:MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class NoticeMyViewHolder(val binding: NoticeItemMainBinding): RecyclerView.ViewHolder(binding.root)
    override fun getItemCount(): Int =datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        NoticeMyViewHolder(NoticeItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as NoticeMyViewHolder).binding
        binding.noticeitemData.text=datas[position]
        binding.noticetime.text=times[position]
        binding.noticeItemRoot.setOnClickListener{
            Log.d("test","item root click: $position")
            //position별로 각각 레이아웃 넘어가게 설정하면되는가
            val intent: Intent = Intent( binding.noticeItemRoot?.context,TestLayout::class.java)
            //startActivity(intent)
            ContextCompat.startActivity( binding.noticeItemRoot.context, intent,null)
        }
    }
}