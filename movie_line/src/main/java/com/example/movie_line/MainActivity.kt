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
import com.google.firebase.ktx.Firebase

// test
class MainActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var  etPassword: EditText
    val MIN_PASSWORD_LENGTH = 6

    lateinit var email_string: String
    lateinit var password_string: String

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewInitializations()
    }

    fun viewInitializations() {
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        email_string = getString(R.string.email)
        password_string = getString(R.string.password)
        hideHint()

        // Initialize Firebase Auth
        auth = Firebase.auth

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun validateInput(): Boolean {
        if (etEmail.text.toString() == "") {
            etEmail.error = "Please Enter Email"
            return false
        }
        if (etPassword.text.toString() == "") {
            etPassword.error = "Please Enter Password"
            return false
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.error = "Please Enter Valid Email"
            return false
        }

        // checking minimum password Length
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.error = "Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters"
            return false
        }
        return true
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Hook Click Event
    fun performLogin(v: View) {
        if (validateInput()) {
// test commmit
            // Input is valid, here send data to your server
            val email = etEmail!!.text.toString()
            val password = etPassword!!.text.toString()
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

            // Here you can call you API

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("firebaseLoginTest", "signInWithEmail:success")
                    val user = auth.currentUser
                    goToMain()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("firebaseLoginTest", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun goToSignup(v: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun goToMain() {
        val intent = Intent(this, MainPage::class.java)
        startActivity(intent)
    }

    fun hideHint() {
        if (etEmail.text!!.isNotEmpty()){ etEmail.hint="" } else etEmail.hint = email_string
        if (etPassword.text!!.isNotEmpty()){etPassword.hint=""} else etPassword.hint = password_string
    }
}
