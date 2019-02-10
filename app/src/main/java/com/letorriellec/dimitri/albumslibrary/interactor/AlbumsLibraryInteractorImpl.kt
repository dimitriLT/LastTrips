package com.letorriellec.dimitri.albumslibrary.interactor

import com.letorriellec.dimitri.albumslibrary.model.Album
import com.letorriellec.dimitri.albumslibrary.repository.AlbumsLibraryRepository
import io.reactivex.Observable

class AlbumsLibraryInteractorImpl(
    private val repository: AlbumsLibraryRepository) :
    AlbumsLibraryInteractor {
    override fun loadAlbums(): Observable<List<Album>>? {

        return repository.executeLoadAlbums()

    }
}