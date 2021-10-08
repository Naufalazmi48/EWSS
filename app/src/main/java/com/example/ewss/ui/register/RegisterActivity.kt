package com.example.ewss.ui.register

import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ewss.R
import com.example.ewss.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setupListener()
        setContentView(binding.root)
    }

    private fun setupListener() {
        with(binding) {
            register.setOnClickListener {
                if (!inputFullname.isNullorEmpty() && !inputUsername.isNullorEmpty()
                    && !inputEmail.isNullorEmpty() && !inputPhoneNumber.isNullorEmpty()
                    && !inputAddress.isNullorEmpty() && !inputPassword.isNullorEmpty()
                    && !inputConfirmPassword.isNullorEmpty()
                ) {

                    if (inputEmail.isEmail() && inputConfirmPassword.isSamePassword() && inputPassword.isValidPassword()) {
                        Toast.makeText(
                            this@RegisterActivity,
                            getString(R.string.register_success, inputUsername.text.toString()),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun EditText.isNullorEmpty(): Boolean {
        with(binding) {
            if (text.toString().isNotEmpty()) {
                fullname.error = null
                username.error = null
                email.error = null
                phoneNumber.error = null
                address.error = null
                password.error = null
                confirmPassword.error = null
                return false
            }
            when (id) {
                R.id.input_fullname -> fullname.error = getString(R.string.column_must_be_filled)
                R.id.input_username -> username.error = getString(R.string.column_must_be_filled)
                R.id.input_email -> email.error = getString(R.string.column_must_be_filled)
                R.id.input_phone_number -> phoneNumber.error =
                    getString(R.string.column_must_be_filled)
                R.id.input_password -> password.error = getString(R.string.column_must_be_filled)
                R.id.input_confirm_password -> confirmPassword.error =
                    getString(R.string.column_must_be_filled)
            }
            return true
        }
    }

    private fun EditText.isSamePassword(): Boolean {
        if (id == R.id.input_confirm_password) {
            if (text.toString() == binding.inputPassword.text.toString()) {
                binding.confirmPassword.error = null
                return true
            } else {
                binding.confirmPassword.error = getString(R.string.invalid_confirm_password)
            }
        }
        return false
    }

    private fun EditText.isEmail(): Boolean {
        if (!text.toString().contains("@")) {
            binding.email.error = getString(R.string.invalid_email)
            return false
        }
        binding.email.error = null
        return true
    }

    private fun EditText.isValidPassword(): Boolean {
        if (id == R.id.input_password) {
            if (text.toString().length >= 8) {
                binding.inputPassword.error = null
                return true
            } else {
                binding.inputPassword.error = getString(R.string.less_character)
            }
        }
        return false
    }
}