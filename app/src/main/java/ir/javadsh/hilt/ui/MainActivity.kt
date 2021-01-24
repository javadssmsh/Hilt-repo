package ir.javadsh.hilt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ir.javadsh.hilt.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


