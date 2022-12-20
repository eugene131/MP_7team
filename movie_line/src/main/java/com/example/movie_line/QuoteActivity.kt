package com.example.movie_line

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.example.movie_line.databinding.ActivityQuoteBinding

class QuoteActivity : AppCompatActivity() {
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)
        val binding= ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle("")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieName = intent.getStringExtra("movieName")
        val movieLine = intent.getStringExtra("movieLine")
        
        //명대사 데이터를 받아서 띄우는 작업이 필요함
        //명대사 페이지 데이터 세팅: 명대사/영화제목/이미지/좋아요수
        fun Quote_page(quote:String, movie_name:String){//모르겠다..
            binding.quote.setText(quote)//명대사
            binding.movieName.setText(movie_name)//영화이름
        }
        //이미지 추가하는 부분

        Quote_page(movieLine.toString(), movieName.toString())

        //좋아요, 댓글, 신고 버튼 클릭 이벤트 구현
        var star=false//별 클릭 여부를 알기 위한 변수
        var heart=false//좋아요 클릭 여부를 알기 위한 변수
        var heart_count=0 //좋아요 카운트
        binding.buttonStar.setOnClickListener{//별 클릭시 이미지 변하게
            if (star==false){//별이 클릭되지 않은 상태에서 클릭
                binding.buttonStar.setBackgroundResource(R.drawable.ic_baseline_star_24)
                star=true
            }
            else{//별이 클릭된 상태에서 다시 클릭해서 해제
                binding.buttonStar.setBackgroundResource(R.drawable.ic_baseline_star_outline_24)
                star=false
            }

        }
        binding.buttonHeart.setOnClickListener{
            if (heart==false){//좋아요가 클릭되지 않은 상태에서 클릭
                binding.buttonHeart.setBackgroundResource(R.drawable.heart_color)
                heart=true
                heart_count++//좋아요 카운트++
            }
            else{//좋아요가 클릭된 상태에서 클릭
                binding.buttonHeart.setBackgroundResource(R.drawable.heart)
                heart=false
                heart_count--//좋아요 카운트--
            }
            //좋아요 클릭 전후로 좋아요 카운트를 다시 표시
            binding.heartCount.setText(Integer.toString(heart_count))
        }

        binding.buttonComment.setOnClickListener{//댓글 버튼을 눌렀을 때 댓글 창이 띄워지도록
            val intent: Intent = Intent(this,TestActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSiren.setOnClickListener{//신고 버튼을 눌렀을 때 신고 창이 띄워지도록
            val intent: Intent = Intent(this,RePortActivity::class.java)
            startActivity(intent)
        }

    }
}