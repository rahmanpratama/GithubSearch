package io.github.rahman.githubsearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.rahman.githubsearch.container.ClientContainer
import io.github.rahman.githubsearch.container.CommonContainer
import io.github.rahman.githubsearch.container.Container
import io.github.rahman.githubsearch.container.ServiceContainer
import io.github.rahman.githubsearch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Container.commonContainer = CommonContainer(this)
        Container.serviceContainer = ServiceContainer()
        Container.clientContainer = ClientContainer()
    }
}
