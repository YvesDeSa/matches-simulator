package me.dio.simulator.domain

import com.google.gson.annotations.SerializedName

data class Match (
    val description: String,

    @SerializedName("location")
    val place: Place,

    @SerializedName("home-team")
    val homeTeam: Team,

    @SerializedName("away-team")
    val awayTeam: Team
)