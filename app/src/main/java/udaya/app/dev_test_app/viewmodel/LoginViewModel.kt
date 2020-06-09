package udaya.app.dev_test_app.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.ResponseBody
import udaya.app.dev_test_app.data.api.ApiHelper
import udaya.app.dev_test_app.data.model.LoginInput
import udaya.app.dev_test_app.data.model.LoginResponse
import udaya.app.dev_test_app.data.roomdb.Data
import udaya.app.dev_test_app.utils.Resource
import java.io.IOException
import java.lang.Exception

class LoginViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    var email : String?=null
    var password : String?=null

    private val loginMessage = MutableLiveData<Resource<String>>()


    fun onLoginButtonClick(view : View){

        loginMessage.postValue(Resource.loading(null))

        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            loginMessage.postValue(Resource.error("Email/Password empty",null))

            return
        }
        viewModelScope.launch{
            try{
                val loginresponse = apiHelper.validateUser(LoginInput(email.toString().trim(),password.toString()))

                     loginresponse.let {

                         if(it.isSuccessful){
                             loginMessage.postValue(Resource.success(it.toString()))
                         }else{
                             loginMessage.postValue(Resource.error("user not found", null))
                         }
                    }

            }catch (e: Exception) {
                loginMessage.postValue(Resource.error(e.message.toString(), null))
            }
            catch (e: IOException) {
                loginMessage.postValue(Resource.error(e.message.toString(), null))
            }
        }
    }

    fun checkLogin(): LiveData<Resource<String>> {
        return loginMessage
    }
}