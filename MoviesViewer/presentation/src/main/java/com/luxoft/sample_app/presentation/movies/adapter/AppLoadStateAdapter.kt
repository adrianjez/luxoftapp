package com.luxoft.sample_app.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luxoft.sample_app.R
import com.luxoft.sample_app.databinding.LoadStateItemBinding

class AppLoadStateAdapter(
        private val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState)
            = holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder
            = LoadStateViewHolder(parent, retry)
}

class LoadStateViewHolder(
        parent: ViewGroup,
        retry: () -> Unit
) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_item, parent, false)
) {
    private val binding = LoadStateItemBinding.bind(itemView)
    private val progressBar: ProgressBar = binding.progressBar
    private val errorMsg: TextView = binding.errorMsg
    private val retry: Button = binding.retryButton
            .also {
                it.setOnClickListener { retry() }
            }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
        }

        progressBar.visibility = if(loadState is LoadState.Loading) View.VISIBLE else View.GONE
        retry.visibility = if(loadState is LoadState.Error) View.VISIBLE else View.GONE
        errorMsg.visibility = if(loadState is LoadState.Error) View.VISIBLE else View.GONE
    }
}

