package udaya.app.dev_test_app.utils

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

@Suppress("DEPRECATION")
class NetworkConnectionInterceptor(
    context : Context
    ):Interceptor {

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {

        if(!IsInternetAvailable()){
            throw IOException("NO Internet")
        }
        else{
            return chain.proceed(chain.request())
        }

    }
    private fun IsInternetAvailable():Boolean{
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it!=null && it.isConnected
        }
    }
}
