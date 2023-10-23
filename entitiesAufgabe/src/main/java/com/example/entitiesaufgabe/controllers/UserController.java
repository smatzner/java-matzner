package com.example.entitiesaufgabe.controllers;

import com.example.entitiesaufgabe.dto.GetUserDTO;
import com.example.entitiesaufgabe.dto.UserDTO;
import com.example.entitiesaufgabe.services.UserService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {

        try {
            userService.registerUser(userDTO);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("User erfolgreich angelegt");
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        List<GetUserDTO> registeredUsers;

        try {
            registeredUsers = userService.deleteUser(userId);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(registeredUsers);
    }
}
