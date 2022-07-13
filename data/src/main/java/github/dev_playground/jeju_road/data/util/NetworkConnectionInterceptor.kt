package github.dev_playground.jeju_road.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isConnectionState()) {
            throw NoConnectiveNetworkException()
        } else {
            chain.proceed(chain.request())
        }
    }

    private fun isConnectionState(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val currentNetwork = connectivityManager.activeNetwork
            val connection = connectivityManager.getNetworkCapabilities(currentNetwork)
            return connection != null && (
                    connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
        } else {
            val activeNetwork = connectivityManager.activeNetworkInfo
            if (activeNetwork != null) {
                return (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                        activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
            }
            return false
        }
    }
}


class NoConnectiveNetworkException : IOException() {
    override val message: String
        get() = "인터넷 연결이 끊켰습니다. \n Wifi 또는 데이터의 상태를 확인해주세요."
}
