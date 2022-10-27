package com.example.server.dto;

import com.example.server.entity.Worker;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BrigadeDto {
    private Integer id;
    private Integer area;
    private Worker brigadier;
    private List<Worker> workers;
}
