package com.letorriellec.dimitri.trips

import com.letorriellec.dimitri.trips.interactor.TripsUseCase
import com.letorriellec.dimitri.trips.interactor.TripsUseCaseImpl
import com.letorriellec.dimitri.trips.model.*
import com.letorriellec.dimitri.trips.repository.SpaceTravelsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Response

@RunWith(JUnit4::class)
class TripsUseCaseImplTest {

    @MockK
    lateinit var repository: SpaceTravelsRepository

    private lateinit var useCase: TripsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = TripsUseCaseImpl(
            repository
        )
    }

    @Test
    fun `when load should get trips`() = runBlocking {
        val trips = listOf(
            SpaceTravel(
                Pilot("name", "url avatar", 1.0f),
                Distance(1, "km"),
                1000,
                PickUp("YAVIN 4", "url picture", "2017-12-09T14:12:51Z"),
                DropOff("NABOO", "url picture", "2017-12-09T19:35:51Z")
            )
        )
        coEvery { repository.executeLoadSpaceTravels() } returns trips


        val result = useCase.loadAlbums()
        assertNotNull(result)
        assert(result is List<SpaceTravel>)
        assertEquals(result, trips)
    }

    @Test
    fun `when load should get empty list`() = runBlocking {
        val trips = emptyList<SpaceTravel>()

        coEvery { repository.executeLoadSpaceTravels() } returns trips

        val result = useCase.loadAlbums()
        assertNotNull(result)
        assert(result is List<SpaceTravel>)
        assertEquals(result, trips)
    }


    @Test
    fun `when load throws an exception`() = runBlocking {

        coEvery { repository.executeLoadSpaceTravels() } throws HttpException(
            Response.error<Any>(
                500,
                ResponseBody.create(MediaType.parse("text/plain"), "Mock Server Error")
            )
        )

        try {
            val result = useCase.loadAlbums()
        } catch (e: Exception) {
            assert(e is HttpException)
        }

    }
}