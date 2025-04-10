package com.kiran.animeapp.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Aired(
    val from: Date?,
    @SerializedName("to") val myto: Date?,
    val prop: Prop,
    val string: String?
)