package io.github.rahman.githubsearch.ui

import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.rahman.githubsearch.R
import io.github.rahman.githubsearch.base.BaseFragment
import io.github.rahman.githubsearch.base.viewBinding
import io.github.rahman.githubsearch.container.Container
import io.github.rahman.githubsearch.databinding.SearchFragmentBinding
import io.github.rahman.githubsearch.helper.EventObserver
import io.github.rahman.githubsearch.helper.SearchViewModelFactory
import io.github.rahman.githubsearch.ui.adapter.ProfileListAdapter

class SearchFragment : BaseFragment(R.layout.search_fragment) {

    private val binding by viewBinding(SearchFragmentBinding::bind)
    private val profileListAdapter: ProfileListAdapter by lazy { ProfileListAdapter(requireContext()) }


    private val viewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory(Container.serviceContainer.scannerService)
    }


    override fun initComponent() {
        super.initComponent()
        binding.profileList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.profileList.setAdapter(profileListAdapter)

    }

    override fun initEventListener() {
        super.initEventListener()
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.search(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
    override fun initObserver() {
        super.initObserver()
        viewModel.liveSuccessGetUsers.observe(viewLifecycleOwner, EventObserver {
            profileListAdapter.reloadData(it.content!!)

        })
    }

    override fun loadData() {
        super.loadData()
    }

}