package com.example.server.dto;

import com.example.server.entity.Brigade;
import com.example.server.entity.EngineeringStaff;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class AreaDto {
    private Integer id;
    private String type;
   /* private List<Brigade> brigade;
    private List<EngineeringStaff> masters;*/
}
