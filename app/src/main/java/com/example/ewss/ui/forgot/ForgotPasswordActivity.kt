package com.example.ewss.ui.forgot

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ewss.R
import com.example.ewss.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)

        setupListener()
        setContentView(binding.root)
    }

    private fun setupListener() {
        with(binding) {
            sendVerificationCode.setOnClickListener {
                with(binding) {
                    if (!inputEmail.isNullorEmpty() && inputEmail.isEmail()) verifyCodeVerification(
                        STATE.VERIFY_CODE
                    )
                }
            }
            verification.setOnClickListener {
                if (!inputCode.isNullorEmpty()) {
                    Toast.makeText(
                        this@ForgotPasswordActivity,
                        getString(R.string.correct_code),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun verifyCodeVerification(state: STATE) {
        with(binding) {
            resetPassword.visibility = View.GONE
            confirmPassword.visibility = View.GONE
            email.visibility = View.GONE
            sendVerificationCode.visibility = View.GONE
            verificationCode.visibility = View.GONE
            verification.visibility = View.GONE
            when (state) {
                STATE.VERIFY_EMAIL -> {
                    email.visibility = View.VISIBLE
                    sendVerificationCode.visibility = View.VISIBLE
                }
                STATE.VERIFY_CODE -> {
                    verificationCode.visibility = View.VISIBLE
                    verification.visibility = View.VISIBLE
                }
                STATE.RESET_PASSWORD -> {
                    resetPassword.visibility = View.VISIBLE
                    confirmPassword.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun EditText.isEmail(): Boolean {
        if (!text.toString().contains("@")) {
            binding.email.error = getString(R.string.invalid_email)
            return false
        }
        binding.email.error = null
        return true
    }

    private fun EditText.isNullorEmpty(): Boolean {
        if (text.toString().isNotEmpty()) {
            binding.email.error = null
            binding.verificationCode.error = null
            return false
        }
        when (id) {
            R.id.input_email -> binding.email.error = getString(R.string.column_must_be_filled)
            R.id.input_code -> binding.verificationCode.error =
                getString(R.string.column_must_be_filled)
        }
        return true
    }

    private enum class STATE {
        VERIFY_EMAIL,
        VERIFY_CODE,
        RESET_PASSWORD
    }
}