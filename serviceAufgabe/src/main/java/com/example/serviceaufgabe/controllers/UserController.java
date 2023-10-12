package com.example.serviceaufgabe.controllers;

import com.example.serviceaufgabe.ServiceAufgabeApplication;
import com.example.serviceaufgabe.dto.GetUserDTO;
import com.example.serviceaufgabe.dto.UserDTO;
import com.example.serviceaufgabe.entities.User;
import com.example.serviceaufgabe.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("User erfolgreich angelegt");
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){
        Set<UserDTO> userDTOS = userService.getUsers();

        return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        List<GetUserDTO> registeredUsers = userService.deleteUser(userId);

        if(registeredUsers == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User nicht gefunden!");

        return ResponseEntity.status(HttpStatus.OK).body(registeredUsers);
    }
}
