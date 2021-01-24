package ir.javadsh.hilt.ui

import android.app.Activity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        childFragmentManager.fragmentFactory = fragmentFactory
    }
}

