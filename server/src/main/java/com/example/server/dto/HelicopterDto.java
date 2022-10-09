package com.example.server.dto;

import com.example.server.entity.Ware.Work;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class HelicopterDto {
    private Integer id;
    private Integer enginePower;
    private List<Work> work = new ArrayList<>();
    private LocalDate startCreate;
    private LocalDate finishCreate;
    private LocalDate startTest;
    private LocalDate finishTest;
    private List<Integer> equipment = new ArrayList<>();
    private Integer shop;
    private Integer lab;
}
