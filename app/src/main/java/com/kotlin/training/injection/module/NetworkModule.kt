package com.kotlin.training.injection.module


import android.app.Application
import com.kotlin.training.data.api.MovieApi
import com.kotlin.training.data.api.NetworkConnectionInterceptor
import com.kotlin.training.utils.Constants.Companion.BASE_URL
import com.kotlin.training.view.ui.BaseApplication
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


@Module
class NetworkModule {

    /**
     * Provides the Post service implementation.
     * @return the Post service implementation.
     */
    @Provides
//    @Reusable
//    @JvmStatic
    internal fun provideMoviesApi(retrofit:Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
//    @Reusable
//    @JvmStatic
    internal fun provideRetrofitInterface(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


    @Provides
//    @JvmStatic
    fun provideHttpClient(interceptor: NetworkConnectionInterceptor, chuck:ChuckInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .addInterceptor(chuck)

                .certificatePinner(certificatePinner)
//            .connectTimeout(60, TimeUnit.SECONDS)
                .build()
    }

    private val certificatePinner = CertificatePinner.Builder()
            .add(BASE_URL, "sha256/0jQVmOH3u5mnMGhGRUCmMKELXOtO9q8i3xfrgq3SfzI")
            .build()

    @Provides
    fun provideInterceptor(application: Application): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(application)
    }

    @Provides
    fun provideChuckInterceptor(application: Application): ChuckInterceptor {
        return ChuckInterceptor(application)
    }
}
