package com.kotlin.training.viewmodel


import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.kotlin.training.MockMoviesList
import com.kotlin.training.data.api.MovieApi
import com.kotlin.training.data.room.dao.MovieDAO
import com.kotlin.training.data.room.entity.Movie
import com.kotlin.training.repository.MovieRepository
import com.kotlin.training.repository.ErrorState
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`


@RunWith(value = JUnit4::class)
class HomeViewModelUnitTest {
    @Mock
    private lateinit var repo: MovieRepository
    private var movieLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    private var statusLiveData: MutableLiveData<ErrorState> = MutableLiveData()
    @Mock
    lateinit var movieDao: MovieDAO
    @Mock
    lateinit var movieApi: MovieApi
    @Mock
    lateinit var application: Application
    private lateinit var testScheduler: Scheduler
    private var movieListMock: MockMoviesList = MockMoviesList()
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {

        movieApi = Mockito.spy<MovieApi>(MovieApi::class.java)
        movieDao = Mockito.spy(MovieDAO::class.java)
        application = Mockito.spy(Application::class.java)
        testScheduler = TestScheduler()

        repo = MovieRepository(movieApi, movieDao)
    }

    @Test
    fun getMoviesTest() {
        `when`(movieDao.getMovieList()).thenReturn(
                Observable.just(movieListMock.results))
        statusLiveData.value?.equals(ErrorState.LOADED)?.let { assert(it) }
    }


    @After
    @Throws(Exception::class)
    fun tearDown() {
        repo.clearDisposable()
    }

}

