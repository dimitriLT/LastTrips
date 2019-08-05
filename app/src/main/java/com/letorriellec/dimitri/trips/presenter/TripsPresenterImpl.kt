package com.letorriellec.dimitri.trips.presenter

import com.letorriellec.dimitri.trips.interactor.TripsUseCase
import com.letorriellec.dimitri.trips.model.SpaceTravel
import com.letorriellec.dimitri.trips.model.SpaceTravelViewModel
import com.letorriellec.dimitri.trips.ui.TripsView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class TripsPresenterImpl(
    private val tripsUseCase: TripsUseCase
) : BasePresenter<TripsView>, TripsPresenter {


    private var view: TripsView? = null
    private var compositeDisposable = CompositeDisposable()

    private val mjob = Job()
    private val scope = CoroutineScope(Dispatchers.IO + mjob)


    private val handler = CoroutineExceptionHandler { _, throwable ->
        presentError(throwable)
    }

    override fun onViewAttached(view: TripsView) {
        this.view = view

    }

    override fun loadTrips() {

        scope.launch(handler) {
            getFromCallback()

        }

    }

    override fun presentError(cause: Throwable) {
        view?.displayError(cause)
    }

    override fun onViewDetach() {
        compositeDisposable.dispose()
        mjob.cancel()
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
        distance = NumberFormat.getNumberInstance(Locale.FRANCE).format(distance.value) + " " + distance.unit,
        arrivalTime = getTimeFromDate(drop_off.date),
        arrivalName = drop_off.name,
        duration = duration.toString(),
        departureName = pick_up.name,
        departureTime = getTimeFromDate(pick_up.date),
        rating = pilot.rating

    )

    private fun getTimeFromDate(date: String): String {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val parseFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        return parseFormat.format(dateFormat.parse(date))

    }

    private suspend fun getFromCallback() {
        val response = tripsUseCase.loadAlbums()

        if (response != null) {
            withContext(Dispatchers.Main) {
                try {
                    presentTrips(response)

                } catch (e: HttpException) {
                    e.cause?.let { presentError(it) }
                } catch (e: Throwable) {
                    presentError(e)
                }
            }
        }
    }

}
