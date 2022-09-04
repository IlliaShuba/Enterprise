package com.example.server.entity.Ware;

import com.example.server.entity.Brigade;
import com.example.server.entity.EngineeringStaff;

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

    @OneToOne(mappedBy = "hangGlider", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Brigade brigade;
}
