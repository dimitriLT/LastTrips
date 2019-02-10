package com.letorriellec.dimitri.albumslibrary

import com.letorriellec.dimitri.albumslibrary.interactor.AlbumsLibraryInteractor
import com.letorriellec.dimitri.albumslibrary.model.Album
import com.letorriellec.dimitri.albumslibrary.model.AlbumViewModel
import com.letorriellec.dimitri.albumslibrary.presenter.AlbumsLibraryPresenter
import com.letorriellec.dimitri.albumslibrary.presenter.AlbumsLibraryPresenterImpl
import com.letorriellec.dimitri.albumslibrary.ui.AlbumsLibraryView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class AlbumsLibraryPresenterImplTest {

    private lateinit var presenter: AlbumsLibraryPresenter
    private val view: AlbumsLibraryView = Mockito.mock(AlbumsLibraryView::class.java)
    private val interactor: AlbumsLibraryInteractor = Mockito.mock(AlbumsLibraryInteractor::class.java)

    @Before
    fun setUp() {
        presenter = AlbumsLibraryPresenterImpl(view, interactor)
    }

    @Test
    fun givenPresentAlbumItems_ShouldDisplayAlbumViewModel() {
        val albumsLibrary = listOf(
            Album(1, 1, "title", "url", "url")
        )

        presenter.presentAlbumsLibrary(albumsLibrary)

        verify(view).displayAlbums(
            listOf(
                AlbumViewModel(
                    "title", "url"))
        )
    }
}
