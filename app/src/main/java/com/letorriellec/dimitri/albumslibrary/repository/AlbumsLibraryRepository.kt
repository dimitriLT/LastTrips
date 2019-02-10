package com.letorriellec.dimitri.albumslibrary.repository

import com.letorriellec.dimitri.albumslibrary.model.Album
import io.reactivex.Observable

interface AlbumsLibraryRepository {

    fun executeLoadAlbums() : Observable<List<Album>>?

}