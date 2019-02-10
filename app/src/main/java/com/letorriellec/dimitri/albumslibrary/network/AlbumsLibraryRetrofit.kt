package com.letorriellec.dimitri.albumslibrary.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class AlbumsLibraryRetrofit {

    companion object {
        fun getInstance(context: Context): AlbumsLibraryApiService? {

            val cacheSize = (5 * 1024 * 1024).toLong()
            val myCache = Cache(context.cacheDir, cacheSize)

            val okHttpClient = OkHttpClient.Builder().cache(myCache).addInterceptor { chain ->
                    var request = chain.request()
                    request = if (hasNetwork(
                            context
                        )!!) {
                        request.newBuilder().
                            header(
                            "Cache-Control", "public, max-age=" + 5)
                            .build()
                    }
                    else {
                        request.newBuilder()
                            .header(
                            "Cache-Control",
                            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                            .build()
                    }
                    chain.proceed(request)
                }.build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://static.leboncoin.fr/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

            return retrofit.create(AlbumsLibraryApiService::class.java)
        }

        private fun hasNetwork(context: Context): Boolean? {
            var isConnected: Boolean? = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected) isConnected = true
            return isConnected
        }

    }
}