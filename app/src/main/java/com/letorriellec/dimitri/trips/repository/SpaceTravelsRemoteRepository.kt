package com.letorriellec.dimitri.trips.repository

import com.letorriellec.dimitri.trips.model.SpaceTravel
import com.letorriellec.dimitri.trips.network.RetrofitFactory
import retrofit2.Response

class SpaceTravelsRemoteRepository {

    suspend fun getSpaceTravelsAsync(): Response<List<SpaceTravel>?>? {
        return RetrofitFactory.getInstance()?.loadTripsAsync()?.await()
    }

}