package com.example.serviceaufgabe.services;

import com.example.serviceaufgabe.ServiceAufgabeApplication;
import com.example.serviceaufgabe.dto.GetUserDTO;
import com.example.serviceaufgabe.dto.UserDTO;
import com.example.serviceaufgabe.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.serviceaufgabe.ServiceAufgabeApplication.articles;
import static com.example.serviceaufgabe.ServiceAufgabeApplication.users;

@RequiredArgsConstructor
@Service
public class UserService {
    public void registerUser(UserDTO userDTO){
        User user = new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getAge()
        );

        users.add(user);
    }

    // TODO: Helper Löschen
    public Set<UserDTO> getUsers(){
        Set<UserDTO> userDTOS = new HashSet<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getAge()
            ));
        }

        return userDTOS;
    }

    public List<GetUserDTO> deleteUser(int userId){
        if(users.removeIf(user -> user.getUserId() == userId)){
            List<GetUserDTO> registeredUsers = new ArrayList<>();

            users.forEach(user -> {
                GetUserDTO getUserDTO = new GetUserDTO(
                        user.getUserId(),
                        user.getUsername(),
                        user.getAge()
                );
                registeredUsers.add(getUserDTO);
            });
            return registeredUsers;
        }

        return null;
    }
}
