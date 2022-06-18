package me.dio.simulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import me.dio.simulator.databinding.ActivityDetailBinding
import me.dio.simulator.domain.Match
import me.dio.simulator.domain.Team

class DetailActivity : AppCompatActivity() {

    object Extras {
        const val MATCH = "EXTRAS_MATCH"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let {
            Glide.with(this).load(it.place.image).into(binding.ivPlace)
            supportActionBar?.title = it.place.name

            binding.tvDescription.text = it.description

            loadTeamOnScreen(it.homeTeam, binding.ivHomeTeam, binding.tvHomeTeamName, binding.rbHomeTeamStars)
            loadTeamOnScreen(it.awayTeam, binding.ivAwayTeam, binding.tvAwayTeamName, binding.rbAwayTeamStars)
        }
    }

    private fun loadTeamOnScreen(team: Team, imageView: ImageView, textView: TextView, ratingBar: RatingBar){
        Glide.with(this).load(team.image).into(imageView)
        textView.text = team.name
        ratingBar.rating = team.stars.toFloat()
    }
}