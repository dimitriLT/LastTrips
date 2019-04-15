package com.letorriellec.dimitri.trips.ui

import com.letorriellec.dimitri.trips.model.SpaceTravelViewModel

interface TripsView {
    fun displayTrips(trips: List<SpaceTravelViewModel>)
    fun displayError(error: Throwable)
    fun displayNoNetworkError()
    fun displayServerUnreachableError()
    fun displayCallFailedError()
    fun displayGenericError(error: Throwable)
}
