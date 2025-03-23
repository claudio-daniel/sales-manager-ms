package com.bk.reactive.app.commerce.admin.my.exception.code

enum class WarningCode(
    val code: String,
    val description: String
) {
    DEFAULT("PLAN-1000", "Undefined warning"),
    SEMINAR_HAS_NO_CLASS("PLAN-1001", "The seminar has no associated class");

    override fun toString(): String = "$code: $description"
}