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
    var movieList = arrayListOf<movie_list>(
        movie_list("조작된 도시", "Action", "2017-02-09", "movie00"),
        movie_list("조작된 도시", "Action", "2017-02-09", "movie01"),
        movie_list("조작된 도시", "Action", "2017-02-09", "movie02"),
        movie_list("조작된 도시", "Action", "2017-02-09", "movie03"),
        movie_list("조작된 도시", "Action", "2017-02-09", "movie04"),
        movie_list("조작된 도시", "Action", "2017-02-09", "movie05"),
        movie_list("조작된 도시", "Action", "2017-02-09", "movie06")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val movieAdapter = MainListAdapter(this, movieList)
        mainListView.adapter = movieAdapter

        mainListView.setOnItemClickListener {parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as movie_list
            Toast.makeText(this, item.name, Toast.LENGTH_SHORT).show()

//            val clickedmovie = movieList[position]
//            // 선택된 목록정보를 가져왔으면 이제 화면 이동
            val myIntent = Intent(this, test_detail::class.java)
//            myIntent.putExtra("movie_info", clickedmovie)
//            System.out.println(position)
//            // 화면 전환
            startActivity(myIntent)
        }
    }

    var comentlist = arrayListOf<coment_list>(
        coment_list("김철수", "조작된 도시 재밌어요", "2017-02-09"),
        coment_list("김영희", "ㄹㅇㄹㅇ", "2017-02-09"),
        coment_list("김민지", "이게 맞나", "2017-02-09"),
        coment_list("안상익", "정렬도 해야겠네", "2017-02-09"),
        coment_list("김현동", "안에 내용 뭐넣지", "2017-02-09"),
        coment_list("김성진", "리스트뷰 사용", "2017-02-09"),
        coment_list("박예은", "긴 문장 테스트ㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄱㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ", "2017-02-09")
    )

   /*override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val comentAdapter = commentListAdapter(this, comentlist)
        mainListView.adapter = comentAdapter
    }*/

}