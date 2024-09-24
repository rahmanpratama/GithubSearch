package io.github.rahman.githubsearch.ui

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import com.bumptech.glide.Glide
import io.github.rahman.githubsearch.R
import io.github.rahman.githubsearch.base.BaseFragment
import io.github.rahman.githubsearch.base.viewBinding
import io.github.rahman.githubsearch.client.dto.GitHubUser
import io.github.rahman.githubsearch.databinding.DlgDetailBinding
import io.github.rahman.githubsearch.databinding.LoginFragmentBinding


internal class DlgDetail(private val context: Context){

    private val dlg: Dialog?
    private val binding: DlgDetailBinding
    init {
        dlg = Dialog(context)
        binding = DlgDetailBinding.inflate(LayoutInflater.from(context))
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(binding.root)
        dlg.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dlg.setCanceledOnTouchOutside(true)
    }

    fun show(data: GitHubUser) {
        Glide.with(context)
            .load(data.avatar_url)
            .into(binding.imageViewDetail)
        Log.d("Log", data.toString())
        binding.textViewName.text = data.login
        binding.textViewId.text = data.id.toString()
        binding.textViewUrl.text = data.url
        binding.background.setOnClickListener {
            dlg?.hide()
        }
        dlg?.show()


    }

    fun hide() {
        dlg?.dismiss()
        dlg?.cancel()
    }
}