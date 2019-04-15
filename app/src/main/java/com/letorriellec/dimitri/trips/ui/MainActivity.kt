package com.letorriellec.dimitri.trips.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.letorriellec.dimitri.trips.interactor.TripsInteractorImpl
import com.letorriellec.dimitri.trips.model.HttpCallFailureException
import com.letorriellec.dimitri.trips.model.NoNetworkException
import com.letorriellec.dimitri.trips.model.ServerUnreachableException
import com.letorriellec.dimitri.trips.model.SpaceTravelViewModel
import com.letorriellec.dimitri.trips.presenter.TripsPresenter
import com.letorriellec.dimitri.trips.presenter.TripsPresenterImpl
import com.letorriellec.dimitri.trips.repository.SpaceTravelsRemoteRepository
import com.letorriellec.dimitri.trips.repository.SpaceTravelsRepositoryImpl
import kotlinx.android.synthetic.main.trips_list.*


class MainActivity : AppCompatActivity() {


    private lateinit var tripsRecyclerViewAdapter: TripsRecyclerViewAdapter
    private lateinit var tripsPresenter : TripsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.letorriellec.dimitri.trips.R.layout.trips_list)

        albumsLibraryRecyclerView.layoutManager = LinearLayoutManager(this)
        albumsLibraryRecyclerView.setHasFixedSize(true)
        tripsRecyclerViewAdapter =
            TripsRecyclerViewAdapter()
        albumsLibraryRecyclerView.adapter = tripsRecyclerViewAdapter


        val albumsLibraryRepository = SpaceTravelsRepositoryImpl(SpaceTravelsRemoteRepository())
        val albumsLibraryInteractor = TripsInteractorImpl(albumsLibraryRepository)
        tripsPresenter = TripsPresenterImpl(albumsLibraryInteractor)

        tripsPresenter.loadTrips()
    }



    private fun setDataInRecyclerView(it: List<SpaceTravelViewModel>) {
        tripsRecyclerViewAdapter.setData(it)
    }

    override fun onStart() {
        super.onStart()
        tripsPresenter.onViewAttached(TripsViewImpl())

    }

    override fun onStop() {
        super.onStop()
        tripsPresenter.onViewDetach()
    }

    inner class TripsViewImpl :
        TripsView {
        override fun displayError(error: Throwable) {
            when (error) {
                is NoNetworkException -> displayNoNetworkError()
                is ServerUnreachableException -> displayServerUnreachableError()
                is HttpCallFailureException -> displayCallFailedError()
                else -> displayGenericError(error)
            }
        }

        override fun displayTrips(trips: List<SpaceTravelViewModel>) {
            setDataInRecyclerView(trips)
        }

        override fun displayNoNetworkError() {
            Toast.makeText(this@MainActivity, "No network!", Toast.LENGTH_SHORT).show()
        }

        override fun displayServerUnreachableError() {
            Toast.makeText(this@MainActivity, "Server is unreachable!", Toast.LENGTH_SHORT).show()
        }

        override fun displayCallFailedError() {
            Toast.makeText(this@MainActivity, "Call failed!", Toast.LENGTH_SHORT).show()
        }

        override fun displayGenericError(error: Throwable) {
            Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
        }

    }
}
