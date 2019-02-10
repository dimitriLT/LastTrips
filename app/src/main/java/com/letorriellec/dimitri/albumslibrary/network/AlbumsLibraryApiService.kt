package com.letorriellec.dimitri.albumslibrary.network

import com.letorriellec.dimitri.albumslibrary.model.Album
import io.reactivex.Observable
import retrofit2.http.GET


interface AlbumsLibraryApiService {

    @GET("img/shared/technical-test.json") fun loadAlbums(): Observable<List<Album>>
}