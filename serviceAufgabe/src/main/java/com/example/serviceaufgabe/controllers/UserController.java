package com.example.serviceaufgabe.controllers;

import com.example.serviceaufgabe.ServiceAufgabeApplication;
import com.example.serviceaufgabe.dto.GetUserDTO;
import com.example.serviceaufgabe.dto.UserDTO;
import com.example.serviceaufgabe.entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @PostMapping
    public String registerUser(@RequestBody UserDTO userDTO) {
        for (User user : ServiceAufgabeApplication.users) {
            if (user.getUserId() == userDTO.getUserId()) {
                return "User bereits vorhanden!";
            }
        }
        User user = new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getAge()
        );

        ServiceAufgabeApplication.users.add(user);
        return "User erfolgreich angelegt";
    }

    @DeleteMapping("{userId}")
    public List<GetUserDTO> deleteUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
        ServiceAufgabeApplication.users.removeIf(user -> user.getUserId() == userId);

        List<GetUserDTO> registeredUsers = new ArrayList<>();

        ServiceAufgabeApplication.users.forEach(user -> {
            GetUserDTO getUserDTO = new GetUserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getAge()
            );
            registeredUsers.add(getUserDTO);
        });

        return registeredUsers;
    }
}
