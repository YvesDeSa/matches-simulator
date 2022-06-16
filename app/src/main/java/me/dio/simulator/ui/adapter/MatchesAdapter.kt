package me.dio.simulator.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.dio.simulator.databinding.MatcheItemBinding
import me.dio.simulator.domain.Match
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
        val context = holder.itemView.context
        val match = matches[position]

        Glide.with(context).load(match.homeTeam.image).into(holder.binding.ivHomeTeam)
        holder.binding.tvHomeTeamName.text = match.homeTeam.name
        if( match.homeTeam.score != null) {
            holder.binding.tvHomeTeamScore.text = match.homeTeam.score.toString()
        }
        Glide.with(context).load(match.awayTeam.image).into(holder.binding.ivAwayTeam)
        holder.binding.tvAwayTeamName.text = match.awayTeam.name
        if( match.awayTeam.score != null) {
            holder.binding.twAwayHomeTeamScore.text = match.awayTeam.score.toString()
        }

        holder.itemView.setOnClickListener{
            var intent = Intent(context, DetailActivity().javaClass)
            intent.putExtra(DetailActivity.Extras.MATCH, match)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    fun getMatches(): List<Match> {
        return matches
    }
}