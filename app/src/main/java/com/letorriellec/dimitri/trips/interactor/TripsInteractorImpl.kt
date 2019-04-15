package com.letorriellec.dimitri.trips.interactor

import com.letorriellec.dimitri.trips.model.SpaceTravel
import com.letorriellec.dimitri.trips.repository.SpaceTravelsRepository
import io.reactivex.Observable

class TripsInteractorImpl(
    private val repository: SpaceTravelsRepository
) : TripsInteractor {

    override fun loadAlbums(): Observable<List<SpaceTravel>>? {
        return repository.executeLoadSpaceTravels()
    }
}