package com.kotlin.training.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.kotlin.training.MockMoviesList
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations


//@RunWith(value = Parameterized::class)
@RunWith(value = JUnit4::class)
class MovieViewModelUnitTest {
    private val voteAverage = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val overview = MutableLiveData<String>()
    private var movieListMock: MockMoviesList = MockMoviesList()
    private var index:Int = 0
    private val mock:String = "Parasite"
    private val mockVote:String = "4.0"
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        title.value = movieListMock.results[index].title
        overview.value = movieListMock.results[index].overview
        voteAverage.value = movieListMock.results[index].vote_average.toString()
    }

    @Test
    fun getMovieTitleBlankTest() {
        assertNotSame(title.value, "")
    }

    @Test
    fun getMovieTitleTest() {
        assertSame(title.value, mock)
    }

    @Test
    fun getOverviewBlankTest() {
        assertNotSame(overview.value, "")
    }

    @Test
    fun getOverviewTest() {
        assertSame(overview.value, mock)
    }
    @Test
    fun getVotingAverageBlankTest() {
        assertNotSame(voteAverage.value, "")
    }

    @Test
    fun getVotingAverageTest() {
        assertEquals(voteAverage.value, mockVote)
    }

}
