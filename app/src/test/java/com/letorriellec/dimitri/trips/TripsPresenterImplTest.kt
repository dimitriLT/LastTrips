package com.letorriellec.dimitri.trips

import com.letorriellec.dimitri.trips.interactor.TripsUseCase
import com.letorriellec.dimitri.trips.model.*
import com.letorriellec.dimitri.trips.presenter.TripsPresenter
import com.letorriellec.dimitri.trips.presenter.TripsPresenterImpl
import com.letorriellec.dimitri.trips.ui.TripsView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class TripsPresenterImplTest {

    private lateinit var presenter: TripsPresenter
    private val view: TripsView = Mockito.mock(TripsView::class.java)
    private val useCase: TripsUseCase = Mockito.mock(TripsUseCase::class.java)
    private val throwable = Mockito.mock(Throwable::class.java)


    @Before
    fun setUp() {

        presenter = TripsPresenterImpl(useCase)
        presenter.onViewAttached(view)

    }

    @Test
    fun givenPresentTrips_ShouldDisplaySpaceTravelViewModel() {
        val trips = listOf(
            SpaceTravel(
                Pilot("name", "url avatar", 1.0f),
                Distance(1, "km"),
                1000,
                PickUp("YAVIN 4", "url picture", "2017-12-09T14:12:51Z"),
                DropOff("NABOO", "url picture", "2017-12-09T19:35:51Z")
            )
        )

        presenter.presentTrips(trips)

        verify(view).displayTrips(
            listOf(
                SpaceTravelViewModel(
                    "name",
                    "url avatar",
                    "1 km",
                    "NABOO",
                    "19:35",
                    "YAVIN 4",
                    "14:12",
                    "1000",
                    1.0f
                )
            )
        )
    }

    @Test
    fun givenPresentError_ShouldDoDisplayErrorMessage() {

        presenter.presentError(throwable)

        verify(view).displayError(throwable)

    }
}
