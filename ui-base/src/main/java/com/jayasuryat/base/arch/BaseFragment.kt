package com.jayasuryat.base.arch

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

public open class BaseFragment : Fragment() {

    private val jobDelegate: Lazy<Job> = lazy { SupervisorJob() }
    private val job by jobDelegate
    protected val uiScope: CoroutineScope by lazy { CoroutineScope(Dispatchers.Main + job) }
    protected val ioScope: CoroutineScope by lazy { CoroutineScope(Dispatchers.IO + job) }

    protected fun NavDirections.navigate(): Unit = findNavController().navigate(this)

    override fun onDestroy() {
        super.onDestroy()
        if (jobDelegate.isInitialized()) jobDelegate.value.cancel()
    }
}