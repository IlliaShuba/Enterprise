package com.example.server.entity.Ware;

import com.example.server.entity.Brigade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "airplane")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeOfWorks;
    private Integer numberOfEngines;
    private LocalDateTime startCreate;
    private LocalDateTime finishCreate;
    private LocalDateTime startTest;
    private LocalDateTime finishTest;

    @OneToOne
    @MapsId
    private Brigade brigade;

}
