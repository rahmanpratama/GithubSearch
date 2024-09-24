package io.github.rahman.githubsearch.ui

import androidx.recyclerview.widget.LinearLayoutManager
import io.github.rahman.githubsearch.R
import io.github.rahman.githubsearch.base.BaseFragment
import io.github.rahman.githubsearch.base.viewBinding
import io.github.rahman.githubsearch.databinding.SearchFragmentBinding
import io.github.rahman.githubsearch.ui.adapter.ProfileListAdapter

class SearchFragment : BaseFragment(R.layout.search_fragment) {

    private val binding by viewBinding(SearchFragmentBinding::bind)
    private val profileListAdapter: ProfileListAdapter by lazy { ProfileListAdapter(requireContext()) }

    override fun initComponent() {
        super.initComponent()

        binding.profileList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.profileList.setAdapter(profileListAdapter)

    }
}