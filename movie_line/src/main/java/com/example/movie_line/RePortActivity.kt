package com.example.movie_line

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RePortActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_page)
        val button: Button = findViewById(R.id.checkbox5)
        val text: EditText = findViewById(R.id.editText)

        button.setOnClickListener {
            text.setText("신고사유를 작성해주세요")
        }
    }
    fun goToMain(view: View) {
        val intent = Intent(this, MainPage::class.java)
        startActivity(intent)
    }
}