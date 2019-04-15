package com.letorriellec.dimitri.trips.repository

import com.letorriellec.dimitri.trips.network.TripsRetrofit
import com.letorriellec.dimitri.trips.model.SpaceTravel
import io.reactivex.Observable

class SpaceTravelsRemoteRepository{

    fun getSpaceTravels(): Observable<List<SpaceTravel>>? {
        return TripsRetrofit.getInstance()?.loadTrips()
    }

}