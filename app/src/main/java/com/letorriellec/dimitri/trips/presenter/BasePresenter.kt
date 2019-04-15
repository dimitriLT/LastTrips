package com.letorriellec.dimitri.trips.presenter

interface BasePresenter<in V> {
    fun onViewDetach()
    fun onViewAttached(view: V)
}