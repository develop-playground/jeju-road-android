package github.dev_playground.jeju_road.data.util

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkHealthCheckingInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code == 500) {
            throw ConnectionShutdownException()
        }
        return response
    }
}

class ConnectionShutdownException : IOException() {
    override val message: String
        get() = "500 ERROR: 서버가 종료되었습니다. 다시 시도해주세요."
}
