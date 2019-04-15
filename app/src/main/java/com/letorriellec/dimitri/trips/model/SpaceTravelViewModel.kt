package com.letorriellec.dimitri.trips.model

import java.io.Serializable

data class SpaceTravelViewModel(
    val pilotName: String,
    val pilotAvatar: String,
    val distance: String,
    val arrivalName: String,
    val arrivalTime: String,
    val departureName: String,
    val departureTime: String,
    val duration: String,
    val rating: Float
) : Serializable