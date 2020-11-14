package ir.javadsh.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
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

class SomeInterfaceImplementation
@Inject
constructor() : SomeInterface {

    override fun getString(): String {
        return "A Thing !"
    }
}

interface SomeInterface {
    fun getString(): String
}

@InstallIn(ActivityComponent::class)
@Module
abstract class MyModule() {

    @ActivityScoped
    @Binds
    abstract fun bindSomeDependency(
        someImp: SomeInterfaceImplementation
    ): SomeInterface

    @ActivityScoped
    @Binds
    abstract fun bindGson(
        gSon: Gson
    ): Gson

}


