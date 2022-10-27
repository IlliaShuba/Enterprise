package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EngineerDto {
    private Integer id;
    private String name;
    private String lastname;
    private String speciality;
    private Integer area;
}
