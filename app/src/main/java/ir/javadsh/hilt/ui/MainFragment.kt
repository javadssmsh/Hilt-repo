package ir.javadsh.hilt.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ir.javadsh.hilt.R
import ir.javadsh.hilt.model.Blog
import ir.javadsh.hilt.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder

@AndroidEntryPoint
class MainFragment constructor(
    private val someString: String
) : Fragment(R.layout.fragment_main) {

    private val TAG: String = "MainFragment"
    lateinit var text: TextView
    lateinit var progressBar: ProgressBar

    @ExperimentalCoroutinesApi
    private val viewModel: MainViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "Here is some string : ${someString}")

        text = view.findViewById(R.id.massage_text)
        progressBar = view.findViewById(R.id.progress_bar_loading)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { datastate ->
            when (datastate) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(datastate.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(datastate.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            text.text = message
        } else {
            text.text = getString(R.string.unknown_error)
        }
    }

    private fun displayProgressBar(isDisplay: Boolean) {
        progressBar.visibility = if (isDisplay) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n")
        }
        text.text = sb.toString()
    }
}