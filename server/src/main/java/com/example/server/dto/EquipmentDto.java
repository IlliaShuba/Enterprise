package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EquipmentDto {
    private Integer id;
    private String type;
    private Integer number_laboratory;
}
