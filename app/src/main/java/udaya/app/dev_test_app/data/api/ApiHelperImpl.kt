package udaya.app.dev_test_app.data.api

import udaya.app.dev_test_app.data.model.LoginInput
import udaya.app.dev_test_app.data.model.LoginResponse

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(page : Int) = apiService.getUsers(page)

    override suspend fun validateUser(input: LoginInput)=apiService.validateUser(input)


}