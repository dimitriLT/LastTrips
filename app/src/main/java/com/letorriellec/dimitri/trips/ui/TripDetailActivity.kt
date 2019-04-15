package com.letorriellec.dimitri.trips.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.letorriellec.dimitri.trips.BuildConfig
import com.letorriellec.dimitri.trips.model.SpaceTravelViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.trip_detail.*


class TripDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.letorriellec.dimitri.trips.R.layout.trip_detail)

        setSupportActionBar(detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        val trip = intent?.extras?.getSerializable("trip") as? SpaceTravelViewModel

        if (trip != null) {
            ratingBar.rating = trip.rating
            departureValueTextView.text = trip.departureName
            arrivalTimeValueTextView.text = trip.arrivalName
            departureTimeValueTextView.text = trip.departureTime
            arrivalTimeValueTextView.text = trip.arrivalTime
            arrivalValueTextView.text = trip.arrivalName
            Picasso.get()
                .load(BuildConfig.API_ADDRESS + trip.pilotAvatar)
                .into(detailPilotAvatarImageView)
            detailPilotNameTextView.text = trip.pilotName
            durationValueTextView.text = trip.duration
            distanceValueTextView.text = trip.distance

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}