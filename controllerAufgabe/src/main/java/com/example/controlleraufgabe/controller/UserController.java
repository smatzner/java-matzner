package com.example.controlleraufgabe.controller;

import com.example.controlleraufgabe.ControllerAufgabeApplication;
import com.example.controlleraufgabe.dto.UserDTO;
import com.example.controlleraufgabe.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @PostMapping
    public String registerUser(@RequestBody UserDTO userDTO){
        for(User user : ControllerAufgabeApplication.users){
            if(user.getUserId() == userDTO.getUserId()){
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
}
