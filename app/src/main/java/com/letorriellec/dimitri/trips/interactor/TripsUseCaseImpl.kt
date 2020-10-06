package com.letorriellec.dimitri.trips.interactor

import com.letorriellec.dimitri.trips.model.SpaceTravel
import com.letorriellec.dimitri.trips.repository.SpaceTravelsRepository

class TripsUseCaseImpl(
    private val repository: SpaceTravelsRepository
) : TripsUseCase {

    override suspend fun loadAlbums(): List<SpaceTravel>? {

        return repository.executeLoadSpaceTravels()
    }
}