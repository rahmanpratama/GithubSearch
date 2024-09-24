package io.github.rahman.githubsearch.ui

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.rahman.githubsearch.R
import io.github.rahman.githubsearch.base.BaseFragment
import io.github.rahman.githubsearch.base.viewBinding
import io.github.rahman.githubsearch.container.Container
import io.github.rahman.githubsearch.databinding.LoginFragmentBinding

class LoginFragment : BaseFragment(R.layout.login_fragment) {

    private val binding by viewBinding(LoginFragmentBinding::bind)

    override fun initEventListener() {
        super.initEventListener()

        binding.submitButton.setOnClickListener {
            val githubToken = binding.editTextGithubToken.text.toString()

            if (githubToken.isNotEmpty()) {
                Container.commonContainer.cache.authToken = githubToken
                findNavController().navigate(R.id.action_loginFragment_to_searchFragment)
            } else {

                Log.e("LoginFragment", "GitHub token is empty!")
            }
        }
    }
}