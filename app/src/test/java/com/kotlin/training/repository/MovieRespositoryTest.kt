package com.kotlin.training.repository

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kotlin.training.MockMoviesList
import com.kotlin.training.data.api.MovieApi
import com.kotlin.training.data.model.PopularMovies
import com.kotlin.training.data.room.dao.MovieDAO
import com.kotlin.training.data.room.entity.Movie
import com.kotlin.training.utils.Constants
import io.reactivex.Observable.just
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Response


@RunWith(JUnit4::class)
class MovieRepositoryTest {
    private lateinit var repository: MovieRepository

    @Mock
    lateinit var movieDao: MovieDAO

    @Mock
    lateinit var movieApi: MovieApi

    @Mock
    lateinit var application: Application

    private lateinit var testScheduler: Scheduler

    @Mock
     var observer: Observer<List<Movie>>? = null

    private var model: PopularMovies = PopularMovies()

    private var movieListMock: MockMoviesList = MockMoviesList()
    private var statusLiveData: MutableLiveData<ErrorState> = MutableLiveData()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        movieApi = spy<MovieApi>(MovieApi::class.java)
        movieDao = spy(MovieDAO::class.java)
        application = spy(Application::class.java)
        testScheduler = TestScheduler()

        repository = MovieRepository(movieApi, movieDao)
    }

    @Test
    fun loadFromApiTest() {
        statusLiveData.value?.equals(ErrorState.LOADING)?.let { assert(it) }
        `when`(movieApi.getPopularMovies(1)).thenReturn(
                just(Response.success(model)))
        statusLiveData.value?.equals(ErrorState.LOADED)?.let { assert(it) }
    }

    @Test
    fun loadFromDBTest() {
        statusLiveData.value?.equals(ErrorState.LOADING)?.let { assert(it) }
        `when`(movieDao.getMovieList()).thenReturn(
                just(movieListMock.results))
        statusLiveData.value?.equals(ErrorState.LOADED)?.let { assert(it) }
    }

    @Test
    fun insertInDBTest() {
        `when`(movieApi.getPopularMovies(1)).thenReturn(
                just(Response.success(model)))
        assertNull(movieDao.insertMovieList(model.results))
    }

    @Test
    fun testNull() {
        `when`(movieApi?.getPopularMovies(0)).thenReturn(null)
        assertNotNull(model)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }
}