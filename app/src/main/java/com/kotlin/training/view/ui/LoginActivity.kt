package com.kotlin.training.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.kotlin.training.R
import com.kotlin.training.databinding.ActivityLoginBinding
import com.kotlin.training.utils.CrashlyticsUtils
import com.kotlin.training.utils.FirebaseUtils
import com.kotlin.training.utils.Navigation
import com.kotlin.training.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private var errSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

            viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

            binding.viewModel = viewModel

        } catch (exc: Exception) {
            CrashlyticsUtils.logEvent(this,exc.toString())
        }

    }


    private fun showError(errorMessage: Int) {
        errSnackBar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errSnackBar?.show()
    }

    private fun hideError() {
        errSnackBar?.dismiss()
    }

    private fun subscribeUI() {

        viewModel.errorMessage.observe(this, Observer {

                errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        viewModel.loggedIn.observe(this, Observer { loggedIn ->
            if (loggedIn) {
                FirebaseUtils.setUserProperty(
                    getString(R.string.login),
                    binding.etUsername.text.toString()
                )
                FirebaseUtils.logEvent(
                    FirebaseAnalytics.Event.LOGIN, "subscribeUI()"
                )
                Navigation.navigate(this, R.string.Home)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        subscribeUI()
    }

    override fun onResume() {
        super.onResume()
        FirebaseUtils.setCurrentScreen(this, LoginActivity::class.java.name)
    }




}