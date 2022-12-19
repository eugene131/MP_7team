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
        val datas = mutableListOf<String>()//알림 내용을 저장하는 리스트
        val times = mutableListOf<String>()//알림 발생 시간을 저장하는 리스트
        fun add_notice(data:String, time:String){//알림 내용을 세팅하는 함수
            datas.add(data)
            times.add(time)
        }
        //테스트를 위한 알림 추가
        add_notice("댓글 내용1","2022-12-11")
        add_notice("댓글 내용2","2022-12-11")

        //리스트에 있는 것들을 다 보여주는 방식.
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = Notice_Adapter(datas,times)
        binding.recyclerView.adapter = Notice_Adapter(datas,times)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }
}