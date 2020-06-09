package udaya.app.dev_test_app.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import udaya.app.dev_test_app.R
import udaya.app.dev_test_app.data.api.ApiHelperImpl
import udaya.app.dev_test_app.data.api.ApiService
import udaya.app.dev_test_app.data.api.RetrofitBuilder
import udaya.app.dev_test_app.databinding.ActivityLoginBinding
import udaya.app.dev_test_app.utils.LoginViewModelFactory
import udaya.app.dev_test_app.utils.NetworkConnectionInterceptor
import udaya.app.dev_test_app.utils.Status
import udaya.app.dev_test_app.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel : LoginViewModel
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this, R.layout.activity_login)

        val networkConnectionInterceptor= NetworkConnectionInterceptor(this)
        val apiService: ApiService = RetrofitBuilder.getRetrofit(networkConnectionInterceptor).create(ApiService::class.java)


        setUpViewModel(apiService)
        setupObserver()

    }

    private fun setupObserver() {
        viewModel.checkLogin().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility=View.GONE
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Status.LOADING -> {
                    binding.progressBar.visibility=View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility=View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setUpViewModel(apiService:ApiService){
        viewModel = ViewModelProviders.of(this,
            LoginViewModelFactory(ApiHelperImpl(apiService))).get(LoginViewModel::class.java)
        binding.viewmodel= viewModel
    }


}
