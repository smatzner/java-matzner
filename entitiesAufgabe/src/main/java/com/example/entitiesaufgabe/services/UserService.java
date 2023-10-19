package com.example.entitiesaufgabe.services;

import com.example.entitiesaufgabe.dto.GetUserDTO;
import com.example.entitiesaufgabe.dto.UserDTO;
import com.example.entitiesaufgabe.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.entitiesaufgabe.EntitiesAufgabeApplication.users;

@RequiredArgsConstructor
@Service
public class UserService {
    public void registerUser(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .age(userDTO.getAge())
                .build();

        users.add(user);
    }

    // TODO: Helper Löschen
    public Set<UserDTO> getUsers() {
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

    public List<GetUserDTO> deleteUser(int userId) {
        if (users.removeIf(user -> user.getUserId() == userId)) {
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
