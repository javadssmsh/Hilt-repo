package ir.javadsh.hilt.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ir.javadsh.hilt.MainFragment
import ir.javadsh.hilt.MainFragmentFactory
import ir.javadsh.hilt.R
import ir.javadsh.hilt.model.Blog
import ir.javadsh.hilt.utils.DataState
import java.lang.StringBuilder
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {




    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.fragmentFactory = fragmentFactory
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, MainFragment::class.java, null)
            .commit()


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


