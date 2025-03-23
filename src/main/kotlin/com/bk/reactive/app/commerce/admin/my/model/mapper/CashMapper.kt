package com.bk.reactive.app.commerce.admin.my.model.mapper

import com.bk.reactive.app.commerce.admin.my.model.api.response.BasicCashResponse
import com.bk.reactive.app.commerce.admin.my.model.api.response.CashResponse
import com.bk.reactive.app.commerce.admin.my.model.api.response.CashStatusResponse
import com.bk.reactive.app.commerce.admin.my.model.document.Cash
import com.bk.reactive.app.commerce.admin.my.model.document.CashStatus
import com.bk.reactive.app.commerce.admin.my.model.mapper.TurnMapper.Companion.fromTurnToTurnResponse
import com.bk.reactive.app.commerce.admin.my.model.mapper.UserMapper.Companion.fromUserToUserResponse

class CashMapper {
    companion object {
        fun fromCashToBasicCashResponse(cash: Cash) =
            BasicCashResponse(
                id = cash.id!!,
                status = fromCashStatusToCashStatusResponse(cash.cashStatus),
                turn = fromTurnToTurnResponse(cash.turn),
                user = fromUserToUserResponse(cash.user)
            )

        fun fromCashToCashResponse(cash: Cash) =
            CashResponse(
                id = cash.id!!,
                turn = fromTurnToTurnResponse(cash.turn),
                status = fromCashStatusToCashStatusResponse(cash.cashStatus),
                user = fromUserToUserResponse(cash.user),
                startMoney = cash.startMoney,
                endMoney = cash.endMoney,
                startDate = cash.startDate,
                endDate = cash.endDate,
                partialBalance = cash.partialBalance
            )

        fun fromCashStatusToCashStatusResponse(cashStatus: CashStatus) =
            CashStatusResponse(
                id = cashStatus.id,
                name = cashStatus.name
            )
    }
}