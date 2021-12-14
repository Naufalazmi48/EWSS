package com.example.ewss.ui.register

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.core.data.Resource
import com.example.core.domain.model.Register
import com.example.ewss.R
import com.example.ewss.databinding.ActivityRegisterBinding
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setupListener()
        setContentView(binding.root)

        viewModel.registerObserver.observe(this, this::registerObserver)
    }

    private fun registerObserver(resource: Resource<Boolean>) {
        when(resource) {
            is Resource.Error -> {
                binding.loading.isVisible = false
                Toast.makeText(
                    this@RegisterActivity,
                    resource.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            is Resource.Loading -> binding.loading.isVisible = true
            is Resource.Success -> {
                binding.loading.isVisible = false
                Toast.makeText(
                    this@RegisterActivity,
                    getString(R.string.register_success, binding.inputFullname.text.toString()),
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }

    private fun setupListener() {
        with(binding) {
            register.setOnClickListener {
                if (!inputFullname.isNullorEmpty() && !inputCountry.isNullorEmpty()
                    && !inputEmail.isNullorEmpty() && !inputPhoneNumber.isNullorEmpty()
                    && !inputCountry.isNullorEmpty() && !inputPassword.isNullorEmpty()
                    && !inputConfirmPassword.isNullorEmpty()
                ) {

                    if (inputEmail.isEmail() && inputConfirmPassword.isSamePassword() && inputPassword.isValidPassword()) {

                        viewModel.register(getForm())
                    }
                }
            }
        }
    }

    private fun EditText.isNullorEmpty(): Boolean {
        with(binding) {
            if (text.toString().isNotEmpty()) {
                fullname.error = null
                country.error = null
                email.error = null
                phoneNumber.error = null
                city.error = null
                password.error = null
                confirmPassword.error = null
                return false
            }
            when (id) {
                R.id.input_fullname -> fullname.error = getString(R.string.column_must_be_filled)
                R.id.input_country -> country.error = getString(R.string.column_must_be_filled)
                R.id.input_city -> city.error = getString(R.string.column_must_be_filled)
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

    private fun getForm():Register {
        with(binding){
            return Register(
                name = inputFullname.text.toString(),
                email = inputEmail.text.toString(),
                phone = inputPhoneNumber.text.toString(),
                country = inputCountry.text.toString(),
                city = inputCity.text.toString(),
                password = inputPassword.text.toString(),
                passwordConfirmation = inputConfirmPassword.text.toString()
            )
        }
    }
}