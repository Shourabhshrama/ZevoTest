package com.app.zevotest.ui.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.zevotest.SOMETHING_WENT_WRONG
import com.app.zevotest.data.remote.models.Article

import com.app.zevotest.databinding.ActivityMainBinding
import com.app.zevotest.extentions.showToast
import com.app.zevotest.ui.UIState.UIState
import com.app.zevotest.ui.adapter.NewsAdapter
import com.app.zevotest.ui.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setObserver()


    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.postData.collect() { state ->

                when (state) {
                    is UIState.Success -> {

                        showSuccessState(state.data)
                    }

                    is UIState.Failure -> {
                        showErrorState(state.message)

                    }

                    is UIState.Loading -> showLoadingState()
                    else -> {
                        showToast(SOMETHING_WENT_WRONG)
                    }
                }
            }
        }
    }


    private fun showLoadingState() {
        binding.content.progress.visibility = View.VISIBLE
    }

    private fun showSuccessState(data: Flow<List<Article>>) {
        binding.content.progress.visibility = View.GONE
        var layoutManager = LinearLayoutManager(this)
        binding.content.recyclerviewPost.layoutManager = layoutManager

        lifecycleScope.launch {
            data.collect { item ->
                var postAdapter = NewsAdapter(item)
                binding.content.recyclerviewPost.adapter = postAdapter
            }
        }


        val dividerItemDecoration = DividerItemDecoration(
            binding.content.recyclerviewPost.getContext(),
            layoutManager.getOrientation()
        )

        binding.content.recyclerviewPost.addItemDecoration(dividerItemDecoration)
    }

    private fun showErrorState(errorMessage: String) {
        binding.content.progress.visibility = View.GONE

        showToast(errorMessage)

    }


}