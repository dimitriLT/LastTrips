package com.letorriellec.dimitri.albumslibrary.interactor

import com.letorriellec.dimitri.albumslibrary.model.Album
import io.reactivex.Observable

interface AlbumsLibraryInteractor {
    fun loadAlbums() : Observable<List<Album>>?
}