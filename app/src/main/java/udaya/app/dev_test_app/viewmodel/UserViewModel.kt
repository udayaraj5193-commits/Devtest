package udaya.app.dev_test_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import udaya.app.dev_test_app.utils.Resource
import kotlinx.coroutines.launch
import udaya.app.dev_test_app.data.api.ApiHelper
import udaya.app.dev_test_app.data.roomdb.Data
import udaya.app.dev_test_app.data.roomdb.DatabaseHelper

class UserViewModel(private val apiHelper: ApiHelper,private val dbHelper: DatabaseHelper) : ViewModel() {

    private val users = MutableLiveData<Resource<List<Data>>>()

    init {
        fetchUsers()
    }
    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val usersFromDb = dbHelper.getUsers()
                if (usersFromDb.isEmpty()) {
                    val usersToInsertInDB = mutableListOf<Data>()

                    var i =1
                    do{
                        val usersFromApi = apiHelper.getUsers(i)
                            for (apiUser in usersFromApi.data) {
                                val user = Data(
                                    apiUser.id,
                                    apiUser.email,
                                    apiUser.first_name,
                                    apiUser.last_name,
                                    apiUser.avatar
                                )
                                usersToInsertInDB.add(user)
                            }
                        i += 1
                    }while (i<=usersFromApi.total_pages) //Loop through pages

                    dbHelper.insertAll(usersToInsertInDB)
                    users.postValue(Resource.success(usersToInsertInDB))
                }else{
                    users.postValue(Resource.success(usersFromDb))
                }

            } catch (e: Exception) {
                users.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<Data>>> {
        return users
    }
}