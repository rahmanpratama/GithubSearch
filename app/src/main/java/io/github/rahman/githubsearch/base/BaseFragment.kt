package io.github.rahman.githubsearch.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController



open class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    private var callback: ((Boolean) -> Unit)? = null
    fun onPermissionCallback(block: (Boolean) -> Unit) {
        callback = block
    }
    val intentRequestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){callback?.invoke(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
        initEventListener()
        loadData()
        initObserver()

        // listen untuk network error/ unauthorize dll
    }

    protected fun navigate(id: NavDirections) {
        findNavController().navigate(id)
    }

    protected fun navigate(id: NavDirections, option: NavOptions) {
        findNavController().navigate(id, option)
    }

    protected open fun initComponent(){
        Log.d("LOG", "init component")
    }

    protected open fun initEventListener(){
        Log.d("LOG", "init eventlistener")
    }

    protected open fun initObserver(){
        Log.d("LOG", "init observer")
    }

    protected open fun loadData(){
        Log.d("LOG", "init loaddata")
    }

    protected fun showSoftKeyboard(view: View) {
        view.requestFocus()
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}