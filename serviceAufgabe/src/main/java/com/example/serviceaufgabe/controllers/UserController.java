package com.example.serviceaufgabe.controllers;

import com.example.serviceaufgabe.dto.UserPurchasesDTO;
import com.example.serviceaufgabe.dto.GetUserDTO;
import com.example.serviceaufgabe.dto.PurchaseDTO;
import com.example.serviceaufgabe.dto.UserDTO;
import com.example.serviceaufgabe.services.PurchaseService;
import com.example.serviceaufgabe.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final PurchaseService purchaseService;

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

    @PostMapping("{userId}/purchase")
    public ResponseEntity<?> addPurchase(@PathVariable int userId, @RequestBody PurchaseDTO purchaseDTO){
        List<UserPurchasesDTO> userPurchases = purchaseService.addPurchase(userId,purchaseDTO);

        if(userPurchases == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User oder Artikel nicht gefunden");

        return ResponseEntity.status(HttpStatus.OK).body(userPurchases);
    }
}
