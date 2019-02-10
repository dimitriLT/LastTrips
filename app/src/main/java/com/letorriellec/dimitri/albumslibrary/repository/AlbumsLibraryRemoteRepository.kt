package com.letorriellec.dimitri.albumslibrary.repository

import android.content.Context
import com.letorriellec.dimitri.albumslibrary.network.AlbumsLibraryRetrofit
import com.letorriellec.dimitri.albumslibrary.model.Album
import io.reactivex.Observable

class AlbumsLibraryRemoteRepository(private val context: Context) {

    fun getAlbums(): Observable<List<Album>>? {

        return AlbumsLibraryRetrofit.getInstance(context)?.loadAlbums()
    }

}