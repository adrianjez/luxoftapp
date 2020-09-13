package com.luxoft.sample_app.presentation.search.movie.popularlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.luxoft.sample_app.databinding.FragmentPopularMoviesBinding
import com.luxoft.sample_app.di.viewmodel.DaggerViewModelFactory
import com.luxoft.sample_app.presentation.search.adapter.AppLoadStateAdapter
import com.luxoft.sample_app.presentation.search.adapter.MoviesAdapter
import com.luxoft.sample_app.utils.afterTextChangedDelayed
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: PopularMoviesViewModel
    private lateinit var binding: FragmentPopularMoviesBinding

    private val adapter = MoviesAdapter().also {
        it.withLoadStateHeaderAndFooter(
                header = AppLoadStateAdapter(it::retry),
                footer = AppLoadStateAdapter(it::retry)
        )
    }

    private val searchResultAdapter = MoviesAdapter().also {
        it.withLoadStateHeaderAndFooter(
                header = AppLoadStateAdapter(it::retry),
                footer = AppLoadStateAdapter(it::retry)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PopularMoviesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        binding.popularMoviesList.adapter = adapter
        binding.searchResultList.adapter = searchResultAdapter
        binding.searchView.afterTextChangedDelayed { providedQuery ->
            viewModel.searchQuery(providedQuery)
        }
        subscribeUI()
        return binding.root
    }


    private fun subscribeUI() {
        viewModel.popularMovies.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })

        viewModel.searchResultMovies.observe(viewLifecycleOwner, {
                lifecycleScope.launch {
                    it?.let {
                        binding.searchResultList.visibility = View.VISIBLE
                        searchResultAdapter.submitData(it)
                    } ?: run {
                        binding.searchResultList.visibility = View.GONE
                    }
                }
            }
        )
    }
}