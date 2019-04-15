package com.letorriellec.dimitri.trips.network

import com.letorriellec.dimitri.trips.model.SpaceTravel
import io.reactivex.Observable
import retrofit2.http.GET

interface TripsApiService {

    @GET("trips")
    fun loadTrips(): Observable<List<SpaceTravel>>

}