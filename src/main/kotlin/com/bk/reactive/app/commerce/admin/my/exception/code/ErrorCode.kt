package com.bk.reactive.app.commerce.admin.my.exception.code

enum class ErrorCode(
    val code: String,
    val description: String
) {
    DEFAULT("PLAN-000", "Internal server error"),
    INVALID_CASH_STATUS("PLAN-001", "No puedes cerrar la caja desde esta opción.");

    override fun toString(): String = "$code: $description"
}