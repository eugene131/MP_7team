package com.example.team_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recommend_movie.*


class MainActivity : AppCompatActivity() {

    fun Create_movie_list(): ArrayList<movie_list> {//무비 리스트를 만드는 메서드
        var movieList = arrayListOf<movie_list>(//어레이 리스트로 무비 리스트 초기화
            movie_list("조작된 도시1", "2010", "movie00"),
            movie_list("조작된 도시2", "2017", "movie01"),
            movie_list("조작된 도시3", "2019", "movie02"),
            movie_list("조작된 도시4", "2018", "movie03"),
            movie_list("조작된 도시5", "2023", "movie04"),
            movie_list("조작된 도시6", "2022", "movie05"),
            movie_list("조작된 도시7", "2011", "movie06")
        )
        return movieList
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_activity_movie()
    }

    fun view_activity_movie() {//영화 리스트뷰를 보여줌 ================= fixing ==============
         val movieList = Create_movie_list()//무비 리스트를 만들어줌
         val movieAdapter = MainListAdapter(this, movieList)//MainListAdapter를 사용하여 뷰와 이 context를 연결
         mainListView.adapter = movieAdapter //여기까지가 movie리스트를 화면에 뛰우는 작업

         //listView의 item을 클릭하면 화면 전환하는 작업 ============fixing===========
         mainListView.setOnItemClickListener { parent, view, position, id ->
             val item = parent.getItemAtPosition(position) as movie_list//선택한 목록의 정보 받아오기
//            val clickedmovie = movieList[position]
//            // 선택된 목록정보를 가져왔으면 이제 화면 이동
             val myIntent = Intent(this, test_detail(this,item.name,item.year)::class.java)//다른 액티비티에 연결하기 위하 Intent
//            myIntent.putExtra("movie_info", clickedmovie)
            System.out.println(position)
//            // 화면 전환
             startActivity(myIntent)
             Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()
         }
     }

    fun Create_coment_demo():ArrayList<coment_list> {
        var comentlist = arrayListOf<coment_list>(
            coment_list("김철수", "조작된 도시 재밌어요", "2017-02-09"),
            coment_list("김영희", "ㄹㅇㄹㅇ", "2017-02-09"),
            coment_list("김민지", "이게 맞나", "2017-02-09"),
            coment_list("안상익", "정렬도 해야겠네", "2017-02-09"),
            coment_list("김현동", "안에 내용 뭐넣지", "2017-02-09"),
            coment_list("김성진", "리스트뷰 사용", "2017-02-09"),
            coment_list(
                "박예은",
                "긴 문장 테스트ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ",
                "2017-02-09"
            )
        )
        return comentlist
    }

    fun view_acivity_coments(){
        val comentlist = Create_coment_demo()
        val comentAdapter = commentListAdapter(this, comentlist)
        mainListView.adapter = comentAdapter
    }


}