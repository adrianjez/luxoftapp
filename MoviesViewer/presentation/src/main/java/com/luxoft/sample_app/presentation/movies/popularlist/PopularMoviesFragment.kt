package com.luxoft.sample_app.presentation.movies.popularlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.luxoft.sample_app.databinding.FragmentPopularMoviesBinding
import com.luxoft.sample_app.di.viewmodel.DaggerViewModelFactory
import com.luxoft.sample_app.presentation.movies.adapter.AppLoadStateAdapter
import com.luxoft.sample_app.presentation.movies.adapter.MoviesAdapter
import com.luxoft.sample_app.utils.afterTextChangedDelayed
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: PopularMoviesViewModel
    private lateinit var binding: FragmentPopularMoviesBinding

    val adapter = MoviesAdapter()
    val searchResultAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PopularMoviesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        binding.popularMoviesList.adapter = adapter.withLoadStateHeaderAndFooter(
                header = AppLoadStateAdapter(adapter::retry),
                footer = AppLoadStateAdapter(adapter::retry)
        )
        binding.searchResultList.adapter = searchResultAdapter.withLoadStateHeaderAndFooter(
                header = AppLoadStateAdapter(searchResultAdapter::retry),
                footer = AppLoadStateAdapter(searchResultAdapter::retry)
        )
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