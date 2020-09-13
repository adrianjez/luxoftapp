package com.luxoft.sample_app.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.luxoft.domain.model.Movie
import com.luxoft.sample_app.databinding.FragmentPopularMoviesListItemBinding
import com.luxoft.sample_app.presentation.search.movie.popularlist.PopularMoviesFragmentDirections

class MoviesAdapter : PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffUtil()){

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       getItem(position)?.let { 
           holder.bind(it)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
            MovieViewHolder(FragmentPopularMoviesListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
            ))

    class MovieViewHolder(private val binding: FragmentPopularMoviesListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie){
            binding.apply { 
                movie = item
            }
            itemView.setOnClickListener {
                itemView.findNavController().navigate(
                        PopularMoviesFragmentDirections
                                .actionPopularMoviesFragmentToMovieDetailsFragment(item.id)
                )
            }
        }
    }
}
private class MovieDiffUtil : DiffUtil.ItemCallback<Movie>(){
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}