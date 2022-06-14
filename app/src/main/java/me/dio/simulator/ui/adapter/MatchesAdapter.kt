package me.dio.simulator.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.dio.simulator.databinding.MatcheItemBinding
import me.dio.simulator.domain.Match


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

        holder.binding.tvHomeTeamName.text = match.homeTeam.name
        holder.binding.tvAwayTeamName.text = match.awayTeam.name
    }

    override fun getItemCount(): Int {
        return matches.size
    }
}