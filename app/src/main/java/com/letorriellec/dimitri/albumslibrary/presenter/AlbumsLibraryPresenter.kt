package com.letorriellec.dimitri.albumslibrary.presenter

import com.letorriellec.dimitri.albumslibrary.model.Album

interface AlbumsLibraryPresenter {

    fun presentAlbumsLibrary(albumsLibrary: List<Album>)
    fun presentError(cause: Throwable)
    fun presentEmpty()
    fun loadAlbums()

}