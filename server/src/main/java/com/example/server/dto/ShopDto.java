package com.example.server.dto;

import com.example.server.entity.Area;
import com.example.server.entity.EngineeringStaff;
import com.example.server.entity.Laboratory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShopDto {
    private Integer id;
    private List<Area> area;
    private EngineeringStaff head;
    private List<Laboratory> laboratories;

}
