package com.bk.reactive.app.commerce.admin.my.model.mapper

import com.bk.reactive.app.commerce.admin.my.model.api.response.TurnResponse
import com.bk.reactive.app.commerce.admin.my.model.document.Turn

class TurnMapper {
    companion object {
        fun fromTurnToTurnResponse(turn: Turn) =
            TurnResponse(
                id = turn.id,
                name = turn.name
            )
    }
}