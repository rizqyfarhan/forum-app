package com.example.forum.Controller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.forum.R
import com.example.forum.Services.AuthService
import com.example.forum.databinding.ActivityLoginBinding
import com.example.forum.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginSpinner.visibility = View.INVISIBLE
    }

    fun loginLoginAccClicked(view: View) {

        enableSpinner(true)
        val email = binding.editTextLoginUsr.text.toString()
        val password = binding.editTextLoginPass.text.toString()
        hideKeyboard()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            AuthService.loginUser(email, password) { loginSuccess ->
                if (loginSuccess) {
                    AuthService.findUserByEmail(this) { findSuccess ->
                        if (findSuccess) {
                            enableSpinner(false)
                            finish()
                        } else {
                            errorToast()
                        }
                    }
                } else {
                    errorToast()
                }
            }
        } else {
            Toast.makeText(this, "Please fill in both email and password.", Toast.LENGTH_LONG).show()
        }
    }

    fun loginCreateAccClicked(view: View) {
        val loginCreateAccIntent = Intent(this, CreateAccountActivity::class.java)
        startActivity(loginCreateAccIntent)
        finish()
    }

    fun errorToast() {
        Toast.makeText(this, "Something went wrong, please try again.", Toast.LENGTH_LONG).show()
        enableSpinner(false)
    }

    fun enableSpinner(enable: Boolean) {
        if (enable) {
            binding.loginSpinner.visibility = View.VISIBLE
        } else {
            binding.loginSpinner.visibility = View.VISIBLE
        }
        binding.loginLoginAccBtn.isEnabled = !enable
        binding.loginCreateAccBtn.isEnabled = !enable
    }

    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (inputManager.isAcceptingText) {
            inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

}