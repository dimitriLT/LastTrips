package com.letorriellec.dimitri.trips.network

import com.letorriellec.dimitri.trips.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class TripsRetrofit {

    companion object {

        fun getInstance(): TripsApiService? {

            val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().header(
                    "Accept", "Application/JSON"
                ).build()

                chain.proceed(request)
            }.build()

            val retrofit = Retrofit.Builder().baseUrl(BuildConfig.API_ADDRESS)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

            return retrofit.create(TripsApiService::class.java)
        }
    }

}