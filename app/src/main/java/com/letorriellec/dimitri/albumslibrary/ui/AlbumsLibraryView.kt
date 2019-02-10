package com.letorriellec.dimitri.albumslibrary.ui

import com.letorriellec.dimitri.albumslibrary.model.AlbumViewModel

interface AlbumsLibraryView {
    fun displayAlbums(albums: List<AlbumViewModel>)
}
