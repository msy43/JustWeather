package com.msy.justweather.models

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class WeatherElement (
    @SerialName("LocalObservationDateTime")
    val localObservationDateTime: String,

    @SerialName("EpochTime")
    val epochTime: Long,

    @SerialName("WeatherText")
    val weatherText: String,

    @SerialName("WeatherIcon")
    val weatherIcon: Long,

    @SerialName("HasPrecipitation")
    val hasPrecipitation: Boolean,

    @SerialName("PrecipitationType")
    val precipitationType: JsonObject? = null,

    @SerialName("IsDayTime")
    val isDayTime: Boolean,

    @SerialName("Temperature")
    val temperature: ApparentTemperature,

    @SerialName("RealFeelTemperature")
    val realFeelTemperature: ApparentTemperature,

    @SerialName("RealFeelTemperatureShade")
    val realFeelTemperatureShade: ApparentTemperature,

    @SerialName("RelativeHumidity")
    val relativeHumidity: Long,

    @SerialName("IndoorRelativeHumidity")
    val indoorRelativeHumidity: Long,

    @SerialName("DewPoint")
    val dewPoint: ApparentTemperature,

    @SerialName("Wind")
    val wind: Wind,

    @SerialName("WindGust")
    val windGust: WindGust,

    @SerialName("UVIndex")
    val uvIndex: Long,

    @SerialName("UVIndexText")
    val uvIndexText: String,

    @SerialName("Visibility")
    val visibility: ApparentTemperature,

    @SerialName("ObstructionsToVisibility")
    val obstructionsToVisibility: String,

    @SerialName("CloudCover")
    val cloudCover: Long,

    @SerialName("Ceiling")
    val ceiling: ApparentTemperature,

    @SerialName("Pressure")
    val pressure: ApparentTemperature,

    @SerialName("PressureTendency")
    val pressureTendency: PressureTendency,

    @SerialName("Past24HourTemperatureDeparture")
    val past24HourTemperatureDeparture: ApparentTemperature,

    @SerialName("ApparentTemperature")
    val apparentTemperature: ApparentTemperature,

    @SerialName("WindChillTemperature")
    val windChillTemperature: ApparentTemperature,

    @SerialName("WetBulbTemperature")
    val wetBulbTemperature: ApparentTemperature,

    @SerialName("Precip1hr")
    val precip1Hr: ApparentTemperature,

    @SerialName("PrecipitationSummary")
    val precipitationSummary: Map<String, ApparentTemperature>,

    @SerialName("TemperatureSummary")
    val temperatureSummary: TemperatureSummary,

    @SerialName("MobileLink")
    val mobileLink: String,

    @SerialName("Link")
    val link: String
)

@Serializable
data class ApparentTemperature (
    @SerialName("Metric")
    val metric: Imperial,

    @SerialName("Imperial")
    val imperial: Imperial
)

@Serializable
data class Imperial (
    @SerialName("Value")
    val value: Double,

    @SerialName("Unit")
    val unit: String,

    @SerialName("UnitType")
    val unitType: Long,

    @SerialName("Phrase")
    val phrase: String? = null
)

@Serializable
data class PressureTendency (
    @SerialName("LocalizedText")
    val localizedText: String,

    @SerialName("Code")
    val code: String
)

@Serializable
data class TemperatureSummary (
    @SerialName("Past6HourRange")
    val past6HourRange: PastHourRange,

    @SerialName("Past12HourRange")
    val past12HourRange: PastHourRange,

    @SerialName("Past24HourRange")
    val past24HourRange: PastHourRange
)

@Serializable
data class PastHourRange (
    @SerialName("Minimum")
    val minimum: ApparentTemperature,

    @SerialName("Maximum")
    val maximum: ApparentTemperature
)

@Serializable
data class Wind (
    @SerialName("Direction")
    val direction: Direction,

    @SerialName("Speed")
    val speed: ApparentTemperature
)

@Serializable
data class Direction (
    @SerialName("Degrees")
    val degrees: Long,

    @SerialName("Localized")
    val localized: String,

    @SerialName("English")
    val english: String
)

@Serializable
data class WindGust (
    @SerialName("Speed")
    val speed: ApparentTemperature
)
