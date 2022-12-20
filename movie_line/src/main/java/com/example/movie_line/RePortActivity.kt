package com.example.movie_line

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RePortActivity : AppCompatActivity() {
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_page)
        
        val button: Button = findViewById(R.id.checkbox5) //기타 checkButton
        val text: EditText = findViewById(R.id.editText) //사유작성란 editText

        button.setOnClickListener {
            text.setText("신고사유를 작성해주세요") //기타 체크하면 구체적인 사유 작성란
        }
    }
    //신고하기 버튼 누르면 "신고되었습니다"알림창과 메인화면으로 이동
    fun goToMain(view: View) {
        Toast.makeText(this, "신고되었습니다", Toast.LENGTH_SHORT).show() //알림창
        val intent = Intent(this, MainPage::class.java) //메인페이지 화면전환
        startActivity(intent)
    }
}