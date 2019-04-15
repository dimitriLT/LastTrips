package com.letorriellec.dimitri.trips.repository

import com.letorriellec.dimitri.trips.model.SpaceTravel
import io.reactivex.Observable


class SpaceTravelsRepositoryImpl(
    private val spaceTravelsRemoteRepository: SpaceTravelsRemoteRepository) :
    SpaceTravelsRepository {

    override fun executeLoadSpaceTravels(): Observable<List<SpaceTravel>>? {
        return spaceTravelsRemoteRepository.getSpaceTravels()
    }
}