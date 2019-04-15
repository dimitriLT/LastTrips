package com.letorriellec.dimitri.trips.model

data class SpaceTravel(
    val pilot: Pilot,
    val distance: Distance,
    val duration: Int,
    val pick_up: PickUp,
    val drop_off: DropOff
)