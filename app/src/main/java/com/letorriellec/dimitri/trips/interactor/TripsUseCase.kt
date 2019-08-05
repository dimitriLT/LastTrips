package com.letorriellec.dimitri.trips.interactor

import com.letorriellec.dimitri.trips.model.SpaceTravel

interface TripsUseCase {
    suspend fun loadAlbums(): List<SpaceTravel>?
}