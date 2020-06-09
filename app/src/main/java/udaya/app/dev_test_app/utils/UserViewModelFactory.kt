package udaya.app.dev_test_app.utils


import udaya.app.dev_test_app.data.api.ApiHelper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import udaya.app.dev_test_app.data.roomdb.DatabaseHelper
import udaya.app.dev_test_app.viewmodel.LoginViewModel
import udaya.app.dev_test_app.viewmodel.UserViewModel


class UserViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(apiHelper,dbHelper) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(apiHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}