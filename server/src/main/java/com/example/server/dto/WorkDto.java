package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class WorkDto {
    private Integer id;
    private String typeOfWork;
    private String ware;
    private Integer wareId;
    private LocalDate startWork;
    private LocalDate finishWork;
}
