package com.letorriellec.dimitri.trips.repository

import com.letorriellec.dimitri.trips.model.SpaceTravel
import io.reactivex.Observable

interface SpaceTravelsRepository {

    fun executeLoadSpaceTravels() : Observable<List<SpaceTravel>>?

}