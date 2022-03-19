package com.msy.justweather.models

import kotlinx.serialization.*


@Serializable
data class AutoCompleteElement (
    @SerialName("Version")
    val version: Long,

    @SerialName("Key")
    val key: String,

    @SerialName("Type")
    val type: String,

    @SerialName("Rank")
    val rank: Long,

    @SerialName("LocalizedName")
    val localizedName: String,

    @SerialName("Country")
    val country: AdministrativeArea,

    @SerialName("AdministrativeArea")
    val administrativeArea: AdministrativeArea
)

@Serializable
data class AdministrativeArea (
    @SerialName("ID")
    val id: String,

    @SerialName("LocalizedName")
    val localizedName: String
)
