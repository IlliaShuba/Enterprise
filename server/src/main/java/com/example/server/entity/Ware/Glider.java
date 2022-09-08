package com.example.server.entity.Ware;

import com.example.server.entity.Brigade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "glider")
public class Glider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeOfWorks;
    private Integer weight;
    private LocalDateTime startCreate;
    private LocalDateTime finishCreate;
    private LocalDateTime startTest;
    private LocalDateTime finishTest;

    @OneToOne
    @MapsId
    private Brigade brigade;
}
