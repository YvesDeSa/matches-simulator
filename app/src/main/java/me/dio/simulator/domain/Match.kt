package me.dio.simulator.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match (
    val description: String,

    @SerializedName("location")
    val place: Place,

    @SerializedName("home-team")
    val homeTeam: Team,

    @SerializedName("away-team")
    val awayTeam: Team
): Parcelable