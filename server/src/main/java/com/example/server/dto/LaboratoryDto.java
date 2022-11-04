package com.example.server.dto;

import com.example.server.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LaboratoryDto {
    private Integer id;
    private List<Shop> shops;
    private List<Equipment> equipment;
    private List<Worker> workers;

}
