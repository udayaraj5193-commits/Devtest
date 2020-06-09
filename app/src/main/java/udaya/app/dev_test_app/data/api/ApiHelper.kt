package udaya.app.dev_test_app.data.api
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import udaya.app.dev_test_app.data.model.LoginInput
import udaya.app.dev_test_app.data.model.LoginResponse
import udaya.app.dev_test_app.data.model.Users

interface ApiHelper {

    suspend fun getUsers(page:Int): Users

    suspend fun validateUser(input: LoginInput): Response<JSONArray>


}