package com.bk.reactive.app.commerce.admin.my.conf

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZonedDateTime

@Configuration
@EnableSwagger2
class SpringFoxConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .groupName("")
            .directModelSubstitute(LocalDateTime::class.java, String::class.java)
            .directModelSubstitute(LocalDate::class.java, String::class.java)
            .directModelSubstitute(LocalTime::class.java, String::class.java)
            .directModelSubstitute(ZonedDateTime::class.java, String::class.java)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.bk.reactive.app.commerce.admin.my.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiEndPointsInfo())
    }

    private fun apiEndPointsInfo(): ApiInfo {
        return ApiInfoBuilder().title("My Admin")
            .description("My Admin API for manage sales")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.1")
            .build()
    }
}