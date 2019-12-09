package com.arun.journal.network

import android.app.Application
import com.arun.journal.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Factory class for Retrofit api's
 * */
object ApiFactory {
    private lateinit var application: Application
    fun init(application: Application) {
        ApiFactory.application = application
    }

    private const val CONNECTION_TIME_OUT = 15L
    private const val READ_TIME_OUT = 15L
    private const val WRITE_TIME_OUT = 15L
    private var client: OkHttpClient? = null

    /**
     * returns Retrofit api service
     * */
    fun makeRetrofitService(): Api {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        if (client == null) {
            val builder = OkHttpClient.Builder()
            builder.apply {
                connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                addInterceptor(HeaderInterceptor(application))
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(interceptor)
                dispatcher(dispatcher)
            }
            client = builder.build()
        }
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }
}

/**
 * Interceptor for Headers
 * */
class HeaderInterceptor(val application: Application) : Interceptor {
    companion object {
        private const val HEADER_CONTENT_TYPE = "Content-Type"
        private const val HEADER_APPLICATION_JSON = "application/json"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        addHeader(builder)
        request = builder.build()
        return chain.proceed(request)
    }

    private fun addHeader(builder: Request.Builder) {
        builder.addHeader(HEADER_CONTENT_TYPE, HEADER_APPLICATION_JSON)
       }
}