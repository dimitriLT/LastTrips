package com.letorriellec.dimitri.albumslibrary.repository

import com.letorriellec.dimitri.albumslibrary.model.Album
import io.reactivex.Observable


class AlbumsLibraryRepositoryImpl(
    private val albumsLibraryRemoteRepository: AlbumsLibraryRemoteRepository) :
    AlbumsLibraryRepository {

    override fun executeLoadAlbums(): Observable<List<Album>>? {
        return albumsLibraryRemoteRepository.getAlbums()
    }
}