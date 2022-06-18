package me.dio.simulator.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.dio.simulator.databinding.MatcheItemBinding
import me.dio.simulator.domain.Match
import me.dio.simulator.domain.Team
import me.dio.simulator.ui.DetailActivity


class MatchesAdapter(private var matches: List<Match>) :
    RecyclerView.Adapter<MatchesAdapter.ViewHolder>()  {

    class ViewHolder(internal val binding: MatcheItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: MatcheItemBinding = MatcheItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = matches[position]

        loadViewOnScreen(
            getContext(holder),
            match.homeTeam,
            holder.binding.ivHomeTeam,
            holder.binding.tvHomeTeamName,
            holder.binding.tvHomeTeamScore
        )

        loadViewOnScreen(
            getContext(holder),
            match.awayTeam,
            holder.binding.ivAwayTeam,
            holder.binding.tvAwayTeamName,
            holder.binding.twAwayHomeTeamScore
        )

        holder.itemView.setOnClickListener{
            var intent = Intent(getContext(holder), DetailActivity().javaClass)
            intent.putExtra(DetailActivity.Extras.MATCH, match)
            getContext(holder).startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    fun getMatches(): List<Match> {
        return matches
    }

    private fun loadViewOnScreen(
        context: Context,
        team: Team,
        teamImage: ImageView,
        teamName: TextView,
        teamScore: TextView
    ) {

        Glide.with(context).load(team.image).into(teamImage)
        teamName.text = team.name

        if(team.score != null){
            teamScore.text = team.score.toString()
        }
    }

    private fun getContext(holder: ViewHolder): Context{
        return holder.itemView.context
    }
}