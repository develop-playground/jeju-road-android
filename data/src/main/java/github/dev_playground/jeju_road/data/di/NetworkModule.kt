package github.dev_playground.jeju_road.data.di

import com.orhanobut.logger.Logger
import github.dev_playground.jeju_road.data.api.RestaurantApi
import github.dev_playground.jeju_road.data.api.mock.MockRestaurantApi
import github.dev_playground.jeju_road.data.util.NetworkConnectionInterceptor
import github.dev_playground.jeju_road.data.util.ServerExceptionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 20L
private const val TAG = "OkHttp"
const val BASE_URL_KEY = "base_url_key"

val networkModule = module {

    single {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                try {
                    JSONObject(message)
                    Logger.t(TAG).json(message)
                } catch (e: JSONException) {
                    Logger.t(TAG).d(message)
                }
            }
        })
        logging.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .retryOnConnectionFailure(true)
            .addInterceptor(NetworkConnectionInterceptor(androidContext()))
            .addNetworkInterceptor(ServerExceptionInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(get<String>(named(BASE_URL_KEY)))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(RestaurantApi::class.java) }
//    single<RestaurantApi> { MockRestaurantApi(androidContext()) }
}