package com.kiran.animeapp.data.model

import com.google.gson.annotations.SerializedName

data class Prop(
    val from: From,
    @SerializedName("to") val myto: To
)