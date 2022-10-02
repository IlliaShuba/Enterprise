package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AirplaneDto {

    private Integer id;
    private Integer numberOfEngines;
    private LocalDateTime startCreate;
    private LocalDateTime finishCreate;
    private LocalDateTime startTest;
    private LocalDateTime finishTest;
    private Set<Integer> equipment = new HashSet<>();
    private Integer shop;
    private Integer lab;
}
