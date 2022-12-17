package com.example.movie_line

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddActivity : AppCompatActivity() {
    lateinit var etNickname: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etRepeatPassword: EditText
    // for hints
    lateinit var email_string: String
    lateinit var password_string: String
    lateinit var nickname_string: String
    lateinit var repeat_password_string: String
    private lateinit var auth: FirebaseAuth

    val MIN_PASSWORD_LENGTH = 6;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle("")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewInitializations()
    }

    fun viewInitializations() {
        etNickname = findViewById(R.id.et_nickname)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        etRepeatPassword = findViewById(R.id.et_repeat_password)

        //for text hints
        nickname_string = getString(R.string.nickname)
        email_string = getString(R.string.email)
        password_string = getString(R.string.password)
        repeat_password_string = getString(R.string.passwordRepeat)
        hideHint()

        // Initialize Firebase Auth
        auth = Firebase.auth

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun hideHint() {
        if (etNickname.text!!.isNotEmpty()){ etNickname.hint="" } else etNickname.hint = nickname_string
        if (etPassword.text!!.isNotEmpty()){etPassword.hint=""} else etPassword.hint = password_string
        if (etEmail.text!!.isNotEmpty()){ etEmail.hint="" } else etEmail.hint = email_string
        if (etRepeatPassword.text!!.isNotEmpty()){etRepeatPassword.hint=""} else etRepeatPassword.hint = repeat_password_string

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun performSignUp (view: View) {

    }
}