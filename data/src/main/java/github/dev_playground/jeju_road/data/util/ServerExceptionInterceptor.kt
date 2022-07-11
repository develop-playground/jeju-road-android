package github.dev_playground.jeju_road.data.util

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ServerExceptionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        when(response.code) {
            500 -> { throw ConnectionShutdownException() }
            501 -> { throw NotImplementedException() }
            503 -> { throw ServiceNotAvailableException() }
            504 -> { throw GatewayTimeoutException() }
        }

        return response
    }
}

class ConnectionShutdownException : IOException() {
    override val message: String
        get() = "서버에 오류가 발생하여 종료되었습니다. \n 다시 시도해주세요."
}

class NotImplementedException : IOException() {
    override val message: String
        get() = "501 ERROR: 요청한 기능은 서버에서 구현되지 않았습니다. \n 다시 시도해주세요."
}

class ServiceNotAvailableException : IOException() {
    override val message: String
        get() = "503 ERROR: 서버가 잠시 중단되었습니다. \n 다시 시도해주세요."
}

class GatewayTimeoutException : IOException() {
    override val message: String
        get() = "504 ERROR: 서버의 요청 시간이 초과되었습니다. \n 다시 시도해주세요."
}