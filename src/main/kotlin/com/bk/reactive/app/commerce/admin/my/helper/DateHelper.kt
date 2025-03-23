package com.bk.reactive.app.commerce.admin.my.helper

import com.bk.reactive.app.commerce.admin.my.model.constant.Constants
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class DateHelper(
    private val defaultTimeZone: String = Constants.ARGENTINA_TIMEZONE
) {
    companion object {
        private const val DEFAULT_TIMEZONE = "default.timezone"
        private val TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }

    fun dateToLocalDate(inputDate: Date): LocalDate {
        val instant = Instant.ofEpochMilli(inputDate.time)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of(defaultTimeZone))
        return localDateTime.toLocalDate()
    }

    fun dateToIsoFormat(date: LocalDate): LocalDate {
        return LocalDate.parse(date.format(DateTimeFormatter.ISO_DATE))
    }

    fun convertToDateType(localDate: LocalDate): Date {
        return Date.from(localDate.atStartOfDay(ZoneId.of(defaultTimeZone)).toInstant())
    }

    fun dateTimeToArgentinaZone(zonedDateTime: ZonedDateTime): ZonedDateTime {
        return zonedDateTime.withZoneSameInstant(ZoneId.of(defaultTimeZone))
    }

    fun formatZonedDateTimeToHHmm(time: ZonedDateTime): String {
        return time.format(TIME_FORMATTER)
    }

    fun formatLocalDateToddMMyyyy(date: LocalDate): String {
        return date.format(DATE_FORMATTER)
    }

    fun stringToZonedDateTime(
        dateString: String, timeString: String, datePattern: String,
        timePattern: String
    ): ZonedDateTime? {
        val dateFormatter = DateTimeFormatter.ofPattern(datePattern)
        val timeFormatter = DateTimeFormatter.ofPattern(timePattern)
        val date = LocalDate.parse(dateString, dateFormatter)
        val time = LocalTime.parse(timeString, timeFormatter)
        val dateTime = LocalDateTime.of(date, time)
        return ZonedDateTime.of(dateTime, ZoneId.of(defaultTimeZone))
    }

    fun toZonedDateTime(localDate: LocalDate): ZonedDateTime {
        return localDate.atStartOfDay(ZoneId.of(defaultTimeZone))
    }

    fun toZonedDateTime(localDate: LocalDate, localTime: LocalTime): ZonedDateTime {
        return ZonedDateTime.of(localDate, localTime, ZoneId.of(defaultTimeZone))
    }

    fun getNowZonedDateTime(): ZonedDateTime {
        return ZonedDateTime.now().withZoneSameInstant(ZoneId.of(defaultTimeZone))
    }
}