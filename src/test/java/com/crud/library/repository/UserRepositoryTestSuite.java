package com.crud.library.repository;

import com.crud.library.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTestSuite {
private UserRepository userRepository;

@Test
void testAddUser() {
//given
    User user = new User(1,"Paweł2","Staszewski2", LocalDate.of(2023,01,02));
//    when
    userRepository.save(user);
//    then
    long id = user.getUserId();
    Optional<User> readUser = userRepository.findById(id);
    assertTrue(readUser.isPresent());

//    cleanUP
    userRepository.deleteById(id);
    }
}