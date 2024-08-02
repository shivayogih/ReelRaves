package com.findmore.reelraves.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.findmore.reelraves.data.database.AppDatabase
import com.findmore.reelraves.data.repository.FavoritesRepository
import com.findmore.reelraves.data.repository.TvRepository
import com.findmore.reelraves.data.repository.TvRepositoryImpl
import com.findmore.reelraves.data.repository.MovieRepository
import com.findmore.reelraves.data.repository.MovieRepositoryImpl
import com.findmore.reelraves.network.RetrofitService
import com.findmore.reelraves.network.local.ApiConfig
import com.findmore.reelraves.network.local.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideGenreRepository(
        database: AppDatabase,
        retrofitService: RetrofitService
    ): TvRepository {
        return TvRepositoryImpl(database.genreDao(), retrofitService)
    }





    @Provides
    @Singleton
    fun provideMovieRepository(
        database: AppDatabase,
        retrofitService: RetrofitService
    ): MovieRepository {
        return MovieRepositoryImpl(database.movieDao(), retrofitService)
    }


    @Provides
    @Singleton
    fun provideFavoritesRepository(
        database: AppDatabase,
        retrofitService: RetrofitService
    ): FavoritesRepository {
        return FavoritesRepository(database.movieDao(),database.tvSeriesDao(), retrofitService)
    }





    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        val sslSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApiConfig.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }



    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}