package udaya.app.dev_test_app.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import udaya.app.dev_test_app.utils.NetworkConnectionInterceptor

object RetrofitBuilder {

    private const val BASE_URL = "https://reqres.in/api/"

    fun getRetrofit(networkConnectionInterceptor: NetworkConnectionInterceptor): Retrofit {
        val okHttpClient= OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}