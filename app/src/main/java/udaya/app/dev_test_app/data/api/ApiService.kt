
package udaya.app.dev_test_app.data.api
import org.json.JSONArray
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import udaya.app.dev_test_app.data.model.LoginInput
import udaya.app.dev_test_app.data.model.Users

interface ApiService {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Users

    @POST("login")
    suspend fun validateUser(@Body input: LoginInput): Response<JSONArray>

}