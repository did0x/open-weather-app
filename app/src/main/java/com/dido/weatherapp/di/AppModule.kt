package com.dido.weatherapp.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.dido.weatherapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url
            // Add API key as a query parameter to the original URL
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("appid", BuildConfig.API_KEY)
                .build()
            // Create a new request with the modified URL
            val requestWithApiKey = original.newBuilder()
                .url(url)
                .build()

            chain.proceed(requestWithApiKey)
        }
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(app: Application): ChuckerInterceptor {
        val collector = ChuckerCollector(
            context = app,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        return ChuckerInterceptor.Builder(app)
            .collector(collector)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        authInterceptor: Interceptor
    ): OkHttpClient {
        CookieHandler.setDefault(CookieManager(null, CookiePolicy.ACCEPT_ALL))
        val builder = OkHttpClient.Builder()
        builder.followRedirects(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
        builder
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(authInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}