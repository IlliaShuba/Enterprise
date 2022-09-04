package com.example.server.entity.Ware;

import com.example.server.entity.Brigade;

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

    @OneToOne(mappedBy = "missile", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Brigade brigade;
}
