package com.example.server.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "missile")
public class Missile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeOfWorks;
    private Integer chargePower;
    private LocalDateTime startCreate;
    private LocalDateTime finishCreate;
    private LocalDateTime startTest;
    private LocalDateTime finishTest;
}
