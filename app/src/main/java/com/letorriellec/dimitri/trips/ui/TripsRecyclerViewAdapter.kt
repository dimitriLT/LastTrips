package com.letorriellec.dimitri.trips.ui

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.letorriellec.dimitri.trips.BuildConfig
import com.letorriellec.dimitri.trips.R
import com.letorriellec.dimitri.trips.model.SpaceTravelViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.trips_list_item.view.*


class TripsRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {

    fun setData(items: List<SpaceTravelViewModel>) {
        trips = ArrayList(items)
        notifyDataSetChanged()
    }

    private var trips = ArrayList<SpaceTravelViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.trips_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return trips.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pilotName.text = trips[position].pilotName
        Picasso.get().load(BuildConfig.API_ADDRESS + trips[position].pilotAvatar)
            .into(holder.pilotAvatar)
        holder.pickUp.text = trips[position].departureName
        holder.dropOff.text = trips[position].arrivalName
        holder.spaceTravelViewModel = trips[position]
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    lateinit var spaceTravelViewModel: SpaceTravelViewModel
    val pilotName: TextView = view.pilotNameTextView
    val pilotAvatar: ImageView = view.pilotAvatarImageView
    val pickUp: TextView = view.pickUpTextView
    val dropOff: TextView = view.dropOffTextView

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, TripDetailActivity::class.java)
            intent.putExtra("trip", spaceTravelViewModel)
            view.context.startActivity(intent)
        }
    }

}