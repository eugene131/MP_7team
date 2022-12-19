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


//알림 내용, 시간이 담긴 리스트를 받아옴
class Notice_Adapter(val datas: MutableList<String>, val times:MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class NoticeMyViewHolder(val binding: NoticeItemMainBinding): RecyclerView.ViewHolder(binding.root)
    override fun getItemCount(): Int =datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        NoticeMyViewHolder(NoticeItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as NoticeMyViewHolder).binding
        //알림에 들어갈 내용 데이터 세팅
        binding.noticeitemData.text=datas[position]
        binding.noticetime.text=times[position]
        binding.noticeItemRoot.setOnClickListener{//각 아이템을 클릭시 댓글 페이지로 이동하게됨
            Log.d("test","item root click: $position")
            val intent: Intent = Intent( binding.noticeItemRoot?.context,TestLayout::class.java)
            //startActivity(intent)
            ContextCompat.startActivity( binding.noticeItemRoot.context, intent,null)
        }
    }
}