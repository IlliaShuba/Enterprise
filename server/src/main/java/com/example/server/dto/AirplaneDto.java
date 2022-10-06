package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AirplaneDto {

    private Integer id;
    private Integer numberOfEngines;
    private LocalDate startCreate;
    private LocalDate finishCreate;
    private LocalDate startTest;
    private LocalDate finishTest;
    private List<Integer> equipment = new ArrayList<>();
    private Integer shop;
    private Integer lab;
}
