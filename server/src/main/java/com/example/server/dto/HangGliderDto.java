package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class HangGliderDto {
    private Integer id;
    private String typeOfWorks;
    private Integer weight;
    private LocalDateTime startCreate;
    private LocalDateTime finishCreate;
    private LocalDateTime startTest;
    private LocalDateTime finishTest;
    private Set<Integer> equipment = new HashSet<>();
    private Integer shop;
    private Integer area;
    private Integer lab;
}
