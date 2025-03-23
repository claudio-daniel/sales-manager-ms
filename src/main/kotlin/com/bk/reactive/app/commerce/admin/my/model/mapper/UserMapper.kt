package com.bk.reactive.app.commerce.admin.my.model.mapper

import com.bk.reactive.app.commerce.admin.my.model.api.response.UserResponse
import com.bk.reactive.app.commerce.admin.my.model.document.User

class UserMapper {
    companion object {
        fun fromUserToUserResponse(user: User) =
            UserResponse(
                id = user.id,
                fullName = "${user.name} ${user.lastName}",
                username = user.userName
            )
    }
}