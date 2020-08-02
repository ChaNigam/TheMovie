package com.kotlin.training.view.ui



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.kotlin.training.R
import com.kotlin.training.data.room.entity.Movie
import com.kotlin.training.databinding.FragmentMovieListBinding
import com.kotlin.training.injection.components.ViewModelFactory
import com.kotlin.training.repository.ErrorState
import com.kotlin.training.utils.CrashlyticsUtils
import com.kotlin.training.utils.FirebaseUtils
import com.kotlin.training.viewmodel.HomeViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MovieListFragment : DaggerFragment(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    lateinit var viewModel: HomeViewModel
    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var movieList: List<Movie>
    private var errSnackBar: Snackbar? = null
    private lateinit var fragmentMovieListBinding: FragmentMovieListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            fragmentMovieListBinding = FragmentMovieListBinding.bind(view)

            fragmentMovieListBinding.movieList.layoutManager =
                    GridLayoutManager(this.context, 2);
//                    LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

            viewModel = ViewModelProviders.of(this, factory)[HomeViewModel::class.java]
            fragmentMovieListBinding.viewModel = viewModel
            fragmentMovieListBinding.search.setOnQueryTextListener(this)
            fragmentMovieListBinding.search.setOnCloseListener(this)
//            val searchEdit = fragmentMovieListBinding.search.findViewById(R.id.search_src_text) as EditText
//            val searchEdit = fragmentMovieListBinding.search.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
//            searchEdit.height = 100
//            fragmentMovieListBinding.search.findViewById<LinearLayout>(R.id.search_bar)?.run {
//                this.layoutParams = this.layoutParams.apply {
//                    height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80f, resources.displayMetrics).toInt()
//                }
//            }

            subscribeUI()
        } catch (exc: Exception) {
            this.context?.let { CrashlyticsUtils.logEvent(it, exc.toString()) }
        }

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        try {
            val filteredModelList: List<Movie>
            if (newText?.isNotEmpty()!!) {
                filteredModelList =
                        viewModel.movieListAdapter.filter(
                                fragmentMovieListBinding.search.query.toString(),
                                movieList
                        )
            } else {
                filteredModelList =
                        viewModel.movieListAdapter.filter("", movieList)
            }

            viewModel.movieListAdapter.updateMovieList(filteredModelList)

            FirebaseUtils.logEvent(
                    FirebaseAnalytics.Event.SEARCH, "search:" + newText
            )
        } catch (exc: Exception) {
            this.context?.let { CrashlyticsUtils.logEvent(it, exc.toString()) }
        }
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        fragmentMovieListBinding.search.setQuery(query, false)
        fragmentMovieListBinding.search.setIconified(true)
        return false
    }

    override fun onClose(): Boolean {
        try {
            viewModel.movieListAdapter.filter("", movieList)
            viewModel.movieListAdapter.updateMovieList(movieList)
        } catch (exc: Exception) {
            this.context?.let { CrashlyticsUtils.logEvent(it, exc.toString()) }
        }
        return false
    }

    private fun subscribeUI() {
        try {
            viewModel.getMovies().observe(this, Observer { movieList ->
                this.movieList = movieList
                viewModel.movieListAdapter.updateMovieList(movieList)
                fragmentMovieListBinding.search.visibility = View.VISIBLE
            })
            viewModel.getState().observe(this, Observer { status ->
                when (status) {
                    ErrorState.LOADING -> fragmentMovieListBinding.progress.visibility = View.VISIBLE
                    ErrorState.LOADED -> fragmentMovieListBinding.progress.visibility = View.GONE
                    ErrorState.ERROR -> {
                        fragmentMovieListBinding.progress.visibility = View.GONE
                        showError(getString(ErrorState.ERROR.msg))
                    }
                    ErrorState.EMPTY -> {
                        fragmentMovieListBinding.progress.visibility = View.GONE
                        showError(getString(ErrorState.EMPTY.msg))
                    }
                    ErrorState.REQUEST -> {
                        fragmentMovieListBinding.progress.visibility = View.GONE
                        showError(getString(ErrorState.REQUEST.msg))
                    }
                    else -> hideError()
                }
            })

        } catch (exc: Exception) {
            this.context?.let { CrashlyticsUtils.logEvent(it, exc.toString()) }
        }
    }

    private fun showError(errorMessage: String) {
        errSnackBar = Snackbar.make(fragmentMovieListBinding.root, errorMessage, Snackbar.LENGTH_SHORT)
        errSnackBar?.show()
    }

    private fun hideError() {
        errSnackBar?.dismiss()
    }

    override fun onDestroyView() {
        fragmentMovieListBinding.unbind()
        super.onDestroyView()
    }

}
