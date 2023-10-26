package com.example.cinema.dtos;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDTO {
    private int hallId;
    private String title;
    private String mainCharacter;
    private String description;
    private Date premieredAt;
    private String movieVersion;
}
