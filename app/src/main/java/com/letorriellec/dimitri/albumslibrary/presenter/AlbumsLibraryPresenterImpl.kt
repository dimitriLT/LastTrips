package com.letorriellec.dimitri.albumslibrary.presenter

import android.util.Log
import com.letorriellec.dimitri.albumslibrary.ui.AlbumsLibraryView
import com.letorriellec.dimitri.albumslibrary.interactor.AlbumsLibraryInteractor
import com.letorriellec.dimitri.albumslibrary.model.Album
import com.letorriellec.dimitri.albumslibrary.model.AlbumViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class AlbumsLibraryPresenterImpl(
    private val view: AlbumsLibraryView,
    private val albumsLibraryInteractor: AlbumsLibraryInteractor
) : AlbumsLibraryPresenter {


    private var disposable: Disposable? = null


    override fun loadAlbums() {
        disposable = albumsLibraryInteractor.loadAlbums()?.subscribeOn(Schedulers.io())?.observeOn(
            AndroidSchedulers.mainThread()
        )?.subscribe({ result ->
            presentAlbumsLibrary(result)
        }, { error -> presentError(error) }

        )
    }

    override fun presentError(cause: Throwable) {
        Log.e("Album Library : ","error during the extracting data")
    }

    override fun presentEmpty() {
    }

    override fun presentAlbumsLibrary(albumsLibrary: List<Album>) {

        val viewModels = albumsLibrary.map { album ->
            album.toViewModel()
        }
        view.displayAlbums(ArrayList(viewModels))

    }

    private fun Album.toViewModel() = AlbumViewModel(
        albumTitle = title,
        albumPhotoUrl = thumbnailUrl
    )

}
