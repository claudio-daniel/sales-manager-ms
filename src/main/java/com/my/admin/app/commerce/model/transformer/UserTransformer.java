package com.my.admin.app.commerce.model.transformer;

import com.black.food.manager.model.api.response.UserResponse;
import com.black.food.manager.model.entity.User;
import org.springframework.stereotype.Component;

import static com.black.food.manager.model.constant.BusinessModelConstant.SPACE;

@Component
public class UserTransformer {

    public UserResponse transformUserToUserResponse(final User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFullName(user.getName().concat(SPACE).concat(user.getLastName()));
        userResponse.setUsername(user.getUserName());
        return userResponse;
    }
}
