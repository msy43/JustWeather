package com.msy.justweather.models

import kotlinx.serialization.*


@Serializable
data class FiveDayWeather (
    @SerialName("Headline")
    val headline: Headline,

    @SerialName("DailyForecasts")
    val dailyForecasts: List<DailyForecast>
)

@Serializable
data class DailyForecast (
    @SerialName("Date")
    val date: String,

    @SerialName("EpochDate")
    val epochDate: Long,

    @SerialName("Temperature")
    val temperature: Temperature,

    @SerialName("Day")
    val day: Day,

    @SerialName("Night")
    val night: Day,

    @SerialName("Sources")
    val sources: List<String>,

    @SerialName("MobileLink")
    val mobileLink: String,

    @SerialName("Link")
    val link: String
)

@Serializable
data class Day (
    @SerialName("Icon")
    val icon: Long,

    @SerialName("IconPhrase")
    val iconPhrase: String,

    @SerialName("HasPrecipitation")
    val hasPrecipitation: Boolean,

    @SerialName("PrecipitationType")
    val precipitationType: String? = null,

    @SerialName("PrecipitationIntensity")
    val precipitationIntensity: String? = null
)

@Serializable
data class Temperature (
    @SerialName("Minimum")
    val minimum: Imum,

    @SerialName("Maximum")
    val maximum: Imum
)

@Serializable
data class Imum (
    @SerialName("Value")
    val value: Double,

    @SerialName("Unit")
    val unit: String,

    @SerialName("UnitType")
    val unitType: Long
)

@Serializable
data class Headline (
    @SerialName("EffectiveDate")
    val effectiveDate: String,

    @SerialName("EffectiveEpochDate")
    val effectiveEpochDate: Long,

    @SerialName("Severity")
    val severity: Long,

    @SerialName("Text")
    val text: String,

    @SerialName("Category")
    val category: String,

    @SerialName("EndDate")
    val endDate: String,

    @SerialName("EndEpochDate")
    val endEpochDate: Long,

    @SerialName("MobileLink")
    val mobileLink: String,

    @SerialName("Link")
    val link: String
)
