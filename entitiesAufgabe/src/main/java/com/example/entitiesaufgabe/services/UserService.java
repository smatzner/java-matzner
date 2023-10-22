package com.example.entitiesaufgabe.services;

import com.example.entitiesaufgabe.dto.GetUserDTO;
import com.example.entitiesaufgabe.dto.UserDTO;
import com.example.entitiesaufgabe.entities.User;
import com.example.entitiesaufgabe.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void registerUser(UserDTO userDTO) {
        List<User> userList = userRepository.findAll();
        userList.forEach(user -> {
            if(user.getUsername().equalsIgnoreCase(userDTO.getUsername())){
                throw new EntityExistsException("Der Username " + userDTO.getUsername() + " ist bereits vergeben! Bitte einen anderen Username wählen");
            }
        });

        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .age(userDTO.getAge())
                .build();


        userRepository.save(user);
    }

    public List<GetUserDTO> deleteUser(int userId) {
        if(userRepository.findById(userId).isEmpty()){
            throw new NoSuchElementException("Keinen User mit der Id " + userId + " gefunden!");
        }

        userRepository.deleteById(userId);

        List<User> userList = userRepository.findAll();
        List<GetUserDTO> registeredUsers = new ArrayList<>();
        userList.forEach(user -> {
            GetUserDTO getUserDTO = new GetUserDTO(
                    user.getId(),
                    user.getUsername(),
                    user.getAge()
            );
            registeredUsers.add(getUserDTO);
        });

        return registeredUsers;
    }
}
