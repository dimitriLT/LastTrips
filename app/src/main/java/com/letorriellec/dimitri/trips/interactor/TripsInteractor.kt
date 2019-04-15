package com.letorriellec.dimitri.trips.interactor

import com.letorriellec.dimitri.trips.model.SpaceTravel
import io.reactivex.Observable

interface TripsInteractor {
    fun loadAlbums() : Observable<List<SpaceTravel>>?
}