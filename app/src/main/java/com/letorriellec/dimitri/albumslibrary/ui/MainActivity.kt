package com.letorriellec.dimitri.albumslibrary.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.letorriellec.dimitri.albumslibrary.R
import com.letorriellec.dimitri.albumslibrary.interactor.AlbumsLibraryInteractorImpl
import com.letorriellec.dimitri.albumslibrary.model.AlbumViewModel
import com.letorriellec.dimitri.albumslibrary.presenter.AlbumsLibraryPresenterImpl
import com.letorriellec.dimitri.albumslibrary.repository.AlbumsLibraryRemoteRepository
import com.letorriellec.dimitri.albumslibrary.repository.AlbumsLibraryRepositoryImpl
import kotlinx.android.synthetic.main.albums_library_list.*

class MainActivity : AppCompatActivity() {


    private lateinit var albumsLibraryRecyclerViewAdapter: AlbumsLibraryRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.albums_library_list)

        albumsLibraryRecyclerview.layoutManager = LinearLayoutManager(this)
        albumsLibraryRecyclerview.setHasFixedSize(true)
        albumsLibraryRecyclerViewAdapter =
            AlbumsLibraryRecyclerViewAdapter(this)
        albumsLibraryRecyclerview.adapter = albumsLibraryRecyclerViewAdapter

        val albumsLibraryRepository = AlbumsLibraryRepositoryImpl(AlbumsLibraryRemoteRepository(this))
        val albumsLibraryInteractor = AlbumsLibraryInteractorImpl(albumsLibraryRepository)
        val albumsLibraryPresenter = AlbumsLibraryPresenterImpl(AlbumsLibraryViewImpl(),albumsLibraryInteractor)

        albumsLibraryPresenter.loadAlbums()
    }

    private fun setDataInRecyclerView(it: List<AlbumViewModel>) {
        albumsLibraryRecyclerViewAdapter.setData(it)
    }

    inner class AlbumsLibraryViewImpl :
        AlbumsLibraryView {
        override fun displayAlbums(albums: List<AlbumViewModel>) {
            setDataInRecyclerView(albums)
        }

    }
}
