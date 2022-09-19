package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;

@Getter
@Setter
public class AirplaneDto {

    private Integer id;
    private String typeOfWorks;
    private Integer numberOfEngines;
    private LocalDateTime startCreate;
    private LocalDateTime finishCreate;
    private LocalDateTime startTest;
    private LocalDateTime finishTest;

    private Iterable<Integer> equipment = new HashSet<>();
    private Integer shop;
    private Integer area;
    private Integer lab;
}
