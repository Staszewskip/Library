package com.crud.library.mapper;

import com.crud.library.domain.User;
import com.crud.library.domain.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getRegistrationDate()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstname(),
                user.getLastname(),
                user.getRegistrationDate()
        );
    }
}
