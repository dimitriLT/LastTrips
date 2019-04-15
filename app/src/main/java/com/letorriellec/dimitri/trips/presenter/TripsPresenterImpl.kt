package com.letorriellec.dimitri.trips.presenter

import com.letorriellec.dimitri.trips.interactor.TripsInteractor
import com.letorriellec.dimitri.trips.model.SpaceTravel
import com.letorriellec.dimitri.trips.model.SpaceTravelViewModel
import com.letorriellec.dimitri.trips.ui.TripsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class TripsPresenterImpl(
    private val tripsInteractor: TripsInteractor
) : BasePresenter<TripsView>, TripsPresenter {


    private var view: TripsView? = null
    private var compositeDisposable = CompositeDisposable()

    override fun onViewAttached(view: TripsView) {
        this.view = view
    }

    override fun loadTrips() {
        val disposable : Disposable? =
            tripsInteractor.loadAlbums()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(
            AndroidSchedulers.mainThread()
        )?.subscribe({ result ->
            presentTrips(result)
        }, { error -> presentError(error) }

        )
        disposable?.let { compositeDisposable.add(it) }
        disposable?.addTo(compositeDisposable)
    }

    override fun presentError(cause: Throwable) {
        view?.displayError(cause)
    }

    override fun onViewDetach() {
        compositeDisposable.dispose()
    }

    override fun presentEmpty() {
    }

    override fun presentTrips(spaceTravels: List<SpaceTravel>) {

        val viewModels = spaceTravels.map { trip ->
            trip.toViewModel()
        }
        view?.displayTrips(ArrayList(viewModels))

    }

    private fun SpaceTravel.toViewModel() = SpaceTravelViewModel(
        pilotName = pilot.name,
        pilotAvatar = pilot.avatar,
        distance = NumberFormat.getNumberInstance(Locale.US)
                   .format(distance.value) + " " + distance.unit,
        arrivalTime = getTimeFromDate(drop_off.date),
        arrivalName =  drop_off.name,
        duration = duration.toString(),
        departureName = pick_up.name,
        departureTime = getTimeFromDate(pick_up.date),
        rating = pilot.rating

    )

    private fun getTimeFromDate(date : String) : String{

        return SimpleDateFormat("H:mm", Locale.getDefault())
        .format((SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US)
        .parse(date)))
    }

}
