package me.dio.simulator.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import me.dio.simulator.R
import me.dio.simulator.data.MatchesApi
import me.dio.simulator.databinding.ActivityMainBinding
import me.dio.simulator.domain.Match
import me.dio.simulator.ui.adapter.MatchesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var matchesAdapter: MatchesAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var matchesApi: MatchesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHttpClient()
        setupMatchesList()
        setupMatchesRefresh()
        setupFloatingActivityButton()
    }

    private fun setupHttpClient() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://yvesdesa.github.io/matches-simulator-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        matchesApi = retrofit.create(MatchesApi::class.java)
    }

    private fun setupMatchesList() {
        binding.rvMatches.setHasFixedSize(true)
        binding.rvMatches.layoutManager = LinearLayoutManager(this)

        findMatchesFromApi()
    }

    private fun setupMatchesRefresh() {
        binding.srlMatches.setOnRefreshListener { this.findMatchesFromApi() }
    }

    private fun setupFloatingActivityButton() {
        binding.fabSimulate.setOnClickListener { view ->
            view.animate().rotationBy(120f).setDuration(500)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        for(i in 0 until matchesAdapter.itemCount){
                            val match = matchesAdapter.getMatches()[i]
                            match.homeTeam.score = Random.nextInt(match.homeTeam.stars!! + 1)
                            match.awayTeam.score = Random.nextInt(match.awayTeam.stars!! + 1)
                            matchesAdapter.notifyItemChanged(i)
                        }
                    }
                })
        }
    }

    private fun findMatchesFromApi() {
        binding.srlMatches.isRefreshing = true
        matchesApi.getMatches().enqueue(object : Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    val matches = response.body()
                    matchesAdapter = MatchesAdapter(matches!!)
                    binding.rvMatches.adapter = matchesAdapter
                } else {
                    showErrorMessage()
                }
                binding.srlMatches.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                t.message?.let { Log.i("Error", it) }
                binding.srlMatches.isRefreshing = false
                showErrorMessage()
            }

        })
    }

    private fun showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api, Snackbar.LENGTH_LONG).show()
    }
}