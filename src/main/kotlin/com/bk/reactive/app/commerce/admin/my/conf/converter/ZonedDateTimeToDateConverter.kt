package com.bk.reactive.app.commerce.admin.my.conf.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class ZonedDateTimeToDateConverter : Converter<ZonedDateTime, String> {
    override fun convert(source: ZonedDateTime) = source.toString()
}