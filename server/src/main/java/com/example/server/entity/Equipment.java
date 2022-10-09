package com.example.server.entity;

import com.example.server.entity.Ware.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    @JsonBackReference
    private Laboratory laboratory;

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    @JsonBackReference
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "glider_id")
    @JsonBackReference
    private Glider glider;

    @ManyToOne
    @JoinColumn(name = "hang_glider_id")
    @JsonBackReference
    private HangGlider hangGlider;

    @ManyToOne
    @JoinColumn(name = "helicopter_id")
    @JsonBackReference
    private Helicopter helicopter;

    @ManyToOne
    @JoinColumn(name = "missile_id")
    @JsonBackReference
    private Missile missile;

}
