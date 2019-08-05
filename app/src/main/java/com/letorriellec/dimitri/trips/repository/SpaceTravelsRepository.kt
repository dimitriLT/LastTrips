package com.letorriellec.dimitri.trips.repository

import com.letorriellec.dimitri.trips.model.SpaceTravel

interface SpaceTravelsRepository {

    suspend fun executeLoadSpaceTravels(): List<SpaceTravel>?

}