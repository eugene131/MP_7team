package com.example.quotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import com.example.quotes.databinding.ActivityQuoteBinding

class QuoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)
        val binding= ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //설정할 부분: 명대사/영화제목/이미지/좋아요수
        fun Quote_page(quote:String, movie_name:String, movie_image:Int){//모르겠다..
            binding.quote.setText(quote)
            binding.movieName.setText(movie_name)
            //binding.movieImage.setImageResource(R.drawable.movie_image)
            binding.movieImage.setImageResource(movie_image)

        }
        //이미지 추가하는 부분
        Quote_page("명대사","올빼미의 등장인물",R.drawable.thenightowl)
        //좋아요, 댓글, 신고 버튼 클릭 이벤트 구현
        var star=false
        var heart=false
        var heart_count=0
        binding.buttonStar.setOnClickListener{//별 이미지 변하게
            if (star==false){
                binding.buttonStar.setBackgroundResource(R.drawable.ic_baseline_star_24)
                star=true
            }
            else{
                binding.buttonStar.setBackgroundResource(R.drawable.ic_baseline_star_outline_24)
                star=false
            }

        }
        binding.buttonHeart.setOnClickListener{
            if (heart==false){
                binding.buttonHeart.setBackgroundResource(R.drawable.heart_color)
                heart=true
                heart_count++
            }
            else{
                binding.buttonHeart.setBackgroundResource(R.drawable.heart)
                heart=false
                heart_count--
            }
            binding.heartCount.setText(Integer.toString(heart_count))
        }
        binding.buttonComment.setOnClickListener{
            val intent: Intent = Intent(this,TestActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSiren.setOnClickListener{//얘도 이미지 변하고 숫자 +1
            val intent: Intent = Intent(this,TestActivity::class.java)
            startActivity(intent)
        }

    }
}