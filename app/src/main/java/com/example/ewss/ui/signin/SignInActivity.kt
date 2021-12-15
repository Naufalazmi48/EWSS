package com.example.ewss.ui.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.core.data.Resource
import com.example.core.presentation.model.Account
import com.example.ewss.R
import com.example.ewss.databinding.ActivitySignInBinding
import com.example.ewss.ui.forgot.ForgotPasswordActivity
import com.example.ewss.ui.main.MainActivity
import com.example.ewss.ui.register.RegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val viewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)


        setupListener()
        setupObserver()

        viewModel.alreadyLoggedIn().observe(this, {
            if (!it.name.isNullOrEmpty()) {
                startActivity(Intent(this@SignInActivity, MainActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
            }
        })
        setContentView(binding.root)
    }

    private fun setupObserver() {
        viewModel.login.observe(this@SignInActivity, {
            when (it) {
                is Resource.Error -> {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(
                        this@SignInActivity,
                        it.message ?: "Gagal Login",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> binding.loading.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.loading.visibility = View.GONE

                    it.data?.let {login ->
                        val account = Account(
                            avatar = null,
                            name = login.nama,
                            phone = login.phone,
                            email = login.email
                        )
                        viewModel.saveUserOnPreferences(account)
                    }
                }
            }
        })
    }

    private fun setupListener() {
        with(binding) {
            login.setOnClickListener {
                if (!inputEmail.isNullorEmpty() && !inputPassword.isNullorEmpty()) {
                    viewModel.login(inputEmail.text.toString(), inputPassword.text.toString())
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
            binding.email.error = null
            binding.password.error = null
            return false
        }
        when (id) {
            R.id.input_email -> binding.email.error =
                getString(R.string.column_must_be_filled)
            R.id.input_password -> binding.password.error =
                getString(R.string.column_must_be_filled)
        }
        return true
    }
}