package com.letorriellec.dimitri.trips.presenter

import com.letorriellec.dimitri.trips.model.SpaceTravel
import com.letorriellec.dimitri.trips.ui.TripsView

interface TripsPresenter : BasePresenter<TripsView> {

    fun presentTrips(spaceTravels: List<SpaceTravel>)
    fun presentError(cause: Throwable)
    fun presentEmpty()
    fun loadTrips()
}