package com.bk.reactive.app.commerce.admin.my.model.mapper

import com.bk.reactive.app.commerce.admin.my.model.api.response.SaleResponse
import com.bk.reactive.app.commerce.admin.my.model.document.Sale
import com.bk.reactive.app.commerce.admin.my.model.mapper.CashMapper.Companion.fromCashToBasicCashResponse

class SaleMapper {
    companion object {
        fun fromSaleToSaleResponse(sale: Sale) =
            SaleResponse(
                id = sale.id!!,
                orderId = sale.order.id!!,
                cash = fromCashToBasicCashResponse(sale.cash),
                payment = sale.payment,
                returned = sale.returned,
                total = sale.total,
                date = sale.date
            )
    }
}