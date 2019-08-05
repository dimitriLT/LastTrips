package com.letorriellec.dimitri.trips.network

import com.letorriellec.dimitri.trips.model.SpaceTravel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("trips")
    fun loadTripsAsync(): Deferred<Response<List<SpaceTravel>?>>

}