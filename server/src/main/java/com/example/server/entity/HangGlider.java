package com.example.server.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hangGlider")
public class HangGlider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeOfWorks;
    private Integer weight;
    private LocalDateTime startCreate;
    private LocalDateTime finishCreate;
    private LocalDateTime startTest;
    private LocalDateTime finishTest;

}
