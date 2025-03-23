package com.bk.reactive.app.commerce.admin.my.exception.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class Error(
    val message: String,
    val field: String? = null,
    val rejectedValue: Any? = null,
    val code: String? = null,
    val params: List<String>? = null
)