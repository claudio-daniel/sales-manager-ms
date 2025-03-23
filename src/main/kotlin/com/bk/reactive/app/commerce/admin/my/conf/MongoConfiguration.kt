package com.bk.reactive.app.commerce.admin.my.conf

import com.bk.reactive.app.commerce.admin.my.conf.converter.DateToZonedDateTimeConverter
import com.bk.reactive.app.commerce.admin.my.conf.converter.ZonedDateTimeToDateConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions

@Configuration
class MongoConfiguration {

    @Bean
    fun mongoCustomConversions() =
        MongoCustomConversions(
            listOf(DateToZonedDateTimeConverter(), ZonedDateTimeToDateConverter())
        )
}