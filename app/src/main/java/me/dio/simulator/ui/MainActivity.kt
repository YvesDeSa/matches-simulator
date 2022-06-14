package me.dio.simulator.ui

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


class MainActivity : AppCompatActivity() {
    private lateinit var matchesAdapter: RecyclerView.Adapter<MatchesAdapter.ViewHolder>
    private lateinit var binding: ActivityMainBinding
    private lateinit var matchesApi: MatchesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHttpClient()
        setupMatchesList()
        // setupMatchesRefresh()
        // setupFloatingActivityButton()
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

        matchesApi.getMatches().enqueue(object : Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    val matches = response.body()
                    matchesAdapter = MatchesAdapter(matches!!)
                    binding.rvMatches.adapter = matchesAdapter
                } else {
                    showErrorMessage()
                }
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                t.message?.let { Log.i("Error", it) }
                showErrorMessage()
            }

        })
    }

    private fun setupMatchesRefresh() {
        TODO("Not yet implemented setupMatchesRefresh")
    }

    private fun setupFloatingActivityButton() {
        TODO("Not yet implemented setupFloatingActivityButton")
    }

    private fun showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api, Snackbar.LENGTH_LONG).show()
    }
}