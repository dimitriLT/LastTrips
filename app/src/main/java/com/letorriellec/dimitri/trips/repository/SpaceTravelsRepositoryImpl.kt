package com.letorriellec.dimitri.trips.repository

import com.letorriellec.dimitri.trips.model.NetworkException
import com.letorriellec.dimitri.trips.model.SpaceTravel
import retrofit2.HttpException


class SpaceTravelsRepositoryImpl(
    private val spaceTravelsRemoteRepository: SpaceTravelsRemoteRepository
) :
    SpaceTravelsRepository {

    override suspend fun executeLoadSpaceTravels(): List<SpaceTravel>? {
        try {

            return spaceTravelsRemoteRepository.getSpaceTravelsAsync()?.body()

        } catch (e: HttpException) {
            throw NetworkException(e)
        }
    }
}