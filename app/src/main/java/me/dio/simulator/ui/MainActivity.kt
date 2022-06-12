package me.dio.simulator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.dio.simulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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