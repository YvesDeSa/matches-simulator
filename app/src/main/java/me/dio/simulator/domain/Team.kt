package me.dio.simulator.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team (
    val name: String,
    @SerializedName("rantings")
    val stars: Int,
    val image: String,
    var score: Int?
): Parcelable