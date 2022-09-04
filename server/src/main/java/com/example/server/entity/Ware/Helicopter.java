package com.example.server.entity.Ware;

import com.example.server.entity.Brigade;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "helicopter")
public class Helicopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeOfWorks;
    private Integer enginePower;
    private LocalDateTime startCreate;
    private LocalDateTime finishCreate;
    private LocalDateTime startTest;
    private LocalDateTime finishTest;

    @OneToOne(mappedBy = "helicopter", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Brigade brigade;
}
