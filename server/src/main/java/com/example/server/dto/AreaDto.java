package com.example.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class AreaDto {
    private Integer id;
    private String type;
    private ArrayList<Integer> masters;
}
