package com.urvish.logindemo.presentation.login

import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.urvish.logindemo.R
import com.urvish.logindemo.databinding.ActivityLoginBinding
import com.urvish.logindemo.presentation.base.BaseActivity
import com.urvish.logindemo.utility.Utility
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var binding: ActivityLoginBinding

    private lateinit var loginViewModel: LoginViewModel

    override val layout: Int
        get() = R.layout.activity_login

    override fun viewModelSetUp() {
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        loginViewModel.mutableLoginLiveData.observe(this, {
            showProgressBar(false)
            Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun viewSetup() {
        binding = DataBindingUtil.setContentView(this, layout)

        binding.progressBar.visibility = View.GONE
        binding.btnLogin.setOnClickListener {
            Utility.hideKeyboard(this@LoginActivity)
            showProgressBar(true)
            loginViewModel.checkValidationAndCallLoginWs(
                this@LoginActivity,
                binding.edtUsername.text.toString().trim(),
                binding.edtPassword.text.toString().trim()
            )
        }
    }

    private fun showProgressBar(isShow: Boolean) {
        if (isShow) {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnLogin.isEnabled = false
            binding.btnLogin.isClickable = false
        } else {
            binding.progressBar.visibility = View.GONE
            binding.btnLogin.isEnabled = true
            binding.btnLogin.isClickable = true
        }
    }
}