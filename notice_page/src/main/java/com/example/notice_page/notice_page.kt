package com.example.notice_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notice_page.databinding.ActivityNoticePageBinding

class notice_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNoticePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Notice")

        //이 부분 조절해서 발생시키면 될듯 - 알림 클릭시 페이지 넘어가도록 구현해야함
        //알림 타입(댓글/신고) + 댓글 내용 + 시간 정도 조절하면 될듯
        val datas = mutableListOf<String>()
        val times = mutableListOf<String>()
        fun add_comment(data:String, time:String){
            datas.add(data)
            times.add(time)
        }
        add_comment("댓글 내용1","2022-12-11")
        add_comment("댓글 내용2","2022-12-11")
        /*
        for (i in 1..10) {
            datas.add("item $i")
        }
        */

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = Notice_Adapter(datas,times)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this,
            LinearLayoutManager.VERTICAL)
        )
    }
}