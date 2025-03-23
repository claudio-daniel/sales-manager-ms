package com.bk.reactive.app.commerce.admin.my.conf.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class DateToZonedDateTimeConverter : Converter<String, ZonedDateTime> {
    override fun convert(source: String): ZonedDateTime = ZonedDateTime.parse(source)
}