package ir.javadsh.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
    }
}

@AndroidEntryPoint
class MyFragment : Fragment() {

    @Inject
    lateinit var someClass: SomeClass
}

@ActivityScoped
class SomeClass
@Inject
constructor(
    private val someInterfaceImplementation: SomeInterface
) {
    fun doAThing(): String {
        //return "Look I got : ${someInterfaceImplementation.getString()}"
        return "someThing"
    }
}

class SomeInterfaceImpl
@Inject
constructor(
    private val someDependency: String
) : SomeInterface {

    override fun getString(): String {
        return "A Thing !"
    }
}

interface SomeInterface {
    fun getString(): String
}

@InstallIn(ApplicationComponent::class)
@Module
class MyModule() {

    @Singleton
    @Provides
    fun provideSomeString(): String {
        return "some string "
    }

    @Singleton
    @Provides
    fun provideSomeInterface(
        someString: String
    ): SomeInterface {
        return SomeInterfaceImpl(someString)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }


}


