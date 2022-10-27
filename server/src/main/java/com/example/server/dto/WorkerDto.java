package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkerDto {
    private Integer id;
    private String name;
    private String lastname;
    private String type;
    private String category;
    private Integer number_of_space;

}
