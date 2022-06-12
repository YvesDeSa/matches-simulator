package me.dio.simulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.dio.simulator.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupMatchesList()
        setupMatchesRefresh()
        setupFloatingActivityButton()

    }

    private fun setupMatchesList() {
        TODO("Not yet implemented setupMatchesList")
    }

    private fun setupMatchesRefresh() {
        TODO("Not yet implemented setupMatchesRefresh")
    }
    
    private fun setupFloatingActivityButton() {
        TODO("Not yet implemented setupFloatingActivityButton")
    }
}