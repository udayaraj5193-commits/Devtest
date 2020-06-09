package udaya.app.dev_test_app.utils


import udaya.app.dev_test_app.data.api.ApiHelper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import udaya.app.dev_test_app.data.roomdb.DatabaseHelper
import udaya.app.dev_test_app.viewmodel.LoginViewModel
import udaya.app.dev_test_app.viewmodel.UserViewModel


class LoginViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(apiHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}