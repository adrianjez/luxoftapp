package com.luxoft.sample_app.presentation.movies.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.luxoft.sample_app.databinding.FragmentMovieDetailsBinding
import com.luxoft.sample_app.di.viewmodel.DaggerViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieDetailsFragment : DaggerFragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        viewModel.loadMovieDetails(args.movieID)
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI(){
        viewModel.isLoadingDisplayed.observe(viewLifecycleOwner, {
            binding.loadingView.visibility = if(it) View.VISIBLE else View.GONE
        })
    }
}