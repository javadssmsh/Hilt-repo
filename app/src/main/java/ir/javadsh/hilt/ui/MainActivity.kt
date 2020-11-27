package ir.javadsh.hilt.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ir.javadsh.hilt.R
import ir.javadsh.hilt.model.Blog
import ir.javadsh.hilt.utils.DataState
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var text: TextView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.massage_text)
        progressBar = findViewById(R.id.progress_bar_loading)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { datastate ->
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


/*
@AndroidEntryPoint
class MyFragment : Fragment() {

    @Inject
    lateinit var someClass: SomeClass
}

@ActivityScoped
class SomeClass
@Inject
constructor(
    @MyModule.Impl1 private val someInterfaceImpl1: SomeInterface,
    @MyModule.Impl2  private val someInterfaceImpl2: SomeInterface
) {
    fun doAThing1(): String {
        return "Look I got : ${someInterfaceImpl1.getString()}"
        //return "someThing"
    }

    fun doAThing2(): String {
        return "Look I got : ${someInterfaceImpl2.getString()}"
        //return "someThing"
    }
}

class SomeInterfaceImpl1
@Inject
constructor(
) : SomeInterface {
    override fun getString(): String {
        return "A Thing 1!"
    }
}

class SomeInterfaceImpl2
@Inject
constructor(
) : SomeInterface {
    override fun getString(): String {
        return "A Thing 2!"
    }
}

interface SomeInterface {
    fun getString(): String
}

@InstallIn(ApplicationComponent::class)
@Module
class MyModule() {

    @Impl1
    @Singleton
    @Provides
    fun provideSomeInterface1(
    ): SomeInterface {
        return SomeInterfaceImpl1()
    }

    @Impl2
    @Singleton
    @Provides
    fun provideSomeInterface2(
    ): SomeInterface {
        return SomeInterfaceImpl2()
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Impl1

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class Impl2

}
*/


