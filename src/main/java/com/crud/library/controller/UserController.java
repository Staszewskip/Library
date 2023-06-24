package com.crud.library.controller;

import com.crud.library.domain.dto.UserDTO;
import com.crud.library.exception.UserNotFoundException;
import com.crud.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Adding new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User added successfully", content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping(value = "addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Fetching all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All users from database", content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping(value = {"users"})
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.showAllUsers());
    }

    @Operation(summary = "Deleting a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "User not found")
    })
    @DeleteMapping(value = "user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Updating an user. Only firstname, lastname can be updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User updated successfully", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "User not found")
    })
    @PutMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }
}
