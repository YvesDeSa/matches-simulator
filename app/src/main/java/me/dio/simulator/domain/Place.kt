package me.dio.simulator.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place (
    val name: String,
    val image: String
): Parcelable
