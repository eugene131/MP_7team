package com.example.movie_line

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    lateinit var etNickname: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword:EditText
    lateinit var etRepeatPassword:EditText
    // for hints
    lateinit var email_string: String
    lateinit var password_string: String
    lateinit var nickname_string: String
    lateinit var repeat_password_string: String
    private lateinit var auth: FirebaseAuth

    val MIN_PASSWORD_LENGTH = 6;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

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

    // Checking if the input in form is valid
    fun validateInput(): Boolean {
        if (etNickname.text.toString().equals("")) {
            etNickname.setError("Please Enter Nickname")
            return false
        }
        if (etEmail.text.toString().equals("")) {
            etEmail.setError("Please Enter Email")
            return false
        }
        if (etPassword.text.toString().equals("")) {
            etPassword.setError("Please Enter Password")
            return false
        }
        if (etRepeatPassword.text.toString().equals("")) {
            etRepeatPassword.setError("Please Enter Repeat Password")
            return false
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.setError("Please Enter Valid Email")
            return false
        }

        // checking minimum password Length
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters")
            return false
        }

        // Checking if repeat password is same
        if (!etPassword.text.toString().equals(etRepeatPassword.text.toString())) {
            etRepeatPassword.setError("Password does not match")
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun hideHint() {
        if (etNickname.text!!.isNotEmpty()){ etNickname.hint="" } else etNickname.hint = nickname_string
        if (etPassword.text!!.isNotEmpty()){etPassword.hint=""} else etPassword.hint = password_string
        if (etEmail.text!!.isNotEmpty()){ etEmail.hint="" } else etEmail.hint = email_string
        if (etRepeatPassword.text!!.isNotEmpty()){etRepeatPassword.hint=""} else etRepeatPassword.hint = repeat_password_string

    }

    fun goToLoginPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun performSignUp (view: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server
            val nickname = etNickname.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val repeatPassword = etRepeatPassword.text.toString()

            Toast.makeText(this,"회원가입 완료",Toast.LENGTH_SHORT).show()
            // Here you can call you API
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("testFirebase", "createUserWithEmail:success")
                        val user = auth.currentUser
                        goToLoginPage()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("testFirebase", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}