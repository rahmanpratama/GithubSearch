package io.github.rahman.githubsearch.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.rahman.githubsearch.R
import io.github.rahman.githubsearch.databinding.ProfileItemBinding
import io.github.rahman.githubsearch.ui.viewdata.ProfileViewdata


internal class ProfileListAdapter(private val context: Context): RecyclerView.Adapter<ProfileListAdapter.ProfileListViewHolder>() {

    private val datas = ArrayList<ProfileViewdata>()

    inner class ProfileListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ProfileItemBinding.bind(view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.profile_item, parent, false)
        return ProfileListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileListViewHolder, position: Int) {
        val data = datas[position]

    }

    override fun getItemCount(): Int = datas.size

    fun reloadData(paramData: List<ProfileViewdata>){
        datas.clear()
        datas.addAll(paramData)
        notifyDataSetChanged()
    }

    fun reloadDataWithoutNotify(paramData: List<ProfileViewdata>){
        datas.clear()
        datas.addAll(paramData)
    }
}