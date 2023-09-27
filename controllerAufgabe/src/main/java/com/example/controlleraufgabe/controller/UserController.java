package com.example.controlleraufgabe.controller;

import com.example.controlleraufgabe.ControllerAufgabeApplication;
import com.example.controlleraufgabe.dto.PrintUserDTO;
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
        return "User erfolgriech angelegt";
    }

    @DeleteMapping("{userId}")
    public List<PrintUserDTO> deleteUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
        ControllerAufgabeApplication.users.removeIf(user -> user.getUserId() == userId);

        List<PrintUserDTO> registeredUsers = new ArrayList<>();

        ControllerAufgabeApplication.users.forEach(user -> {
            PrintUserDTO printUserDTO = new PrintUserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getAge()
            );
            registeredUsers.add(printUserDTO);
        });

        return registeredUsers;
    }
}
