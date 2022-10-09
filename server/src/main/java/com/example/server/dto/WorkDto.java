package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkDto {
    private Integer id;
    private String typeOfWork;
    private String ware;
    private Integer wareId;
}
