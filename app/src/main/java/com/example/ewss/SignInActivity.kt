package com.example.ewss

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ewss.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        setupListener()
        setContentView(binding.root)
    }

    private fun setupListener() {
        with(binding) {
            login.setOnClickListener {
                if (!inputUsername.isNullorEmpty() && !inputPassword.isNullorEmpty()) {
                    Toast.makeText(
                        this@SignInActivity,
                        getString(R.string.login_success),
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                }
            }

            forgotPassword.setOnClickListener {
                startActivity(Intent(this@SignInActivity, ForgotPasswordActivity::class.java))
            }

            register.setOnClickListener {
                startActivity(Intent(this@SignInActivity, RegisterActivity::class.java))
            }
        }
    }

    private fun EditText.isNullorEmpty(): Boolean {
        if (text.toString().isNotEmpty()) {
            binding.username.error = null
            binding.password.error = null
            return false
        }
        when (id) {
            R.id.input_username -> binding.username.error =
                getString(R.string.column_must_be_filled)
            R.id.input_password -> binding.password.error =
                getString(R.string.column_must_be_filled)
        }
        return true
    }
}