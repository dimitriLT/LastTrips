package com.letorriellec.dimitri.albumslibrary.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.letorriellec.dimitri.albumslibrary.R
import com.letorriellec.dimitri.albumslibrary.model.AlbumViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.albums_library_list_item.view.*

class AlbumsLibraryRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    fun setData(items: List<AlbumViewModel>) {
        albumsTitles = ArrayList(items)
        notifyDataSetChanged()
    }

    private var albumsTitles = ArrayList<AlbumViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.albums_library_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return albumsTitles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = albumsTitles[position].albumTitle
        Picasso.get()
            .load(albumsTitles[position].albumPhotoUrl)
            .into(holder.image)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.titleTextView
    val image: ImageView = view.imageTextView
}