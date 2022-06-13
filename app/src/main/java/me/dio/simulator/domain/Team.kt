package me.dio.simulator.domain

import com.google.gson.annotations.SerializedName

data class Team (
    val name: String,
    @SerializedName("rantings")
    val stars: Int,
    val image: String
)