package com.crud.library.service;

import com.crud.library.domain.User;
import com.crud.library.domain.dto.UserDTO;
import com.crud.library.exception.UserNotFoundException;
import com.crud.library.mapper.UserMapper;
import com.crud.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User saveUser(UserDTO userDTO) {
        User user = userMapper.mapToUser(userDTO);
        userRepository.save(user);
        return user;
    }

    public List<UserDTO> showAllUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.mapToUserDTOList(userList);
    }

    public void deleteUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public UserDTO updateUser(final UserDTO userDTO) throws UserNotFoundException {
        User user = userRepository.findById(userDTO.getUserId()).orElseThrow(UserNotFoundException::new);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        userRepository.save(user);
        return userMapper.mapToUserDTO(user);
    }

}
