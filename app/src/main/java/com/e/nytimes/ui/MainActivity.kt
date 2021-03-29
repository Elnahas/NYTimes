package com.e.nytimes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.nytimes.R
import com.e.nytimes.adapters.MostPopularAdapter
import com.e.nytimes.utils.Resource
import com.e.nytimes.viewmodels.MostPopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mostPopularViewModel: MostPopularViewModel by viewModels()

    lateinit var mostPopularAdapter: MostPopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        setObservers()

    }

    private fun setObservers() {

        lifecycleScope.launchWhenStarted {
            mostPopularViewModel.mostPopularState.collect {
                when (it) {
                    is Resource.Success -> {
                        mostPopularAdapter.differ.submitList(it.data.results)
                        progressBar.isVisible = false
                    }
                    is Resource.Error -> {
                        progressBar.isVisible = false
                    }
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }


    private fun setupRecyclerView() {
        mostPopularAdapter = MostPopularAdapter()
        recycler_view.apply {
            adapter = mostPopularAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }
    }
}