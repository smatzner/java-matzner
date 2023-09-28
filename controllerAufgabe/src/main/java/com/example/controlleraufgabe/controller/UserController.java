package com.example.controlleraufgabe.controller;

import com.example.controlleraufgabe.ControllerAufgabeApplication;
import com.example.controlleraufgabe.dto.GetUserDTO;
import com.example.controlleraufgabe.dto.UserDTO;
import com.example.controlleraufgabe.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @PostMapping
    public String registerUser(@RequestBody UserDTO userDTO) {
        for (User user : ControllerAufgabeApplication.users) {
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

        ControllerAufgabeApplication.users.add(user);
        return "User erfolgreich angelegt";
    }

    @DeleteMapping("{userId}")
    public List<GetUserDTO> deleteUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
        ControllerAufgabeApplication.users.removeIf(user -> user.getUserId() == userId);

        List<GetUserDTO> registeredUsers = new ArrayList<>();

        ControllerAufgabeApplication.users.forEach(user -> {
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
