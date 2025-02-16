package com.FluffyTerror.Joom2.service.user;

import com.FluffyTerror.Joom2.dto.UserDto;
import com.FluffyTerror.Joom2.model.User;
import com.FluffyTerror.Joom2.request.CreateUserRequest;
import com.FluffyTerror.Joom2.request.UpdateUserRequest;

public interface IUserService {
    User getUserById(Long UserId);
    User createUser(CreateUserRequest user);
    User updateUser(UpdateUserRequest user, Long userId);
    void deleteUser(Long userId);

    UserDto convertToDto(User user);

    User getAuthenticatedUser();
}
