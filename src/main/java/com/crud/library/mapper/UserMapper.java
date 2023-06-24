package com.crud.library.mapper;

import com.crud.library.domain.User;
import com.crud.library.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final BorrowMapper borrowMapper;
    public User mapToUser(final UserDTO userDTO) {
        return new User(
                userDTO.getFirstName(),
                userDTO.getLastName()
        );
    }

    public UserDTO mapToUserDTO(final User user) {
        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getRegistrationDate(),
                borrowMapper.mapToBorrowRecordDTOList(user.getBorrowRecordList())
        );
    }
    public List<UserDTO> mapToUserDTOList(List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }
}
