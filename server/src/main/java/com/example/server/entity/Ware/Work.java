package com.example.server.entity.Ware;

import com.example.server.entity.Area;
import com.example.server.entity.Brigade;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeOfWork;
    private LocalDateTime startWork;
    private LocalDateTime finishWork;

    @ManyToOne
    @JoinColumn(name = "area_id")
    @JsonBackReference
    private Area area;

    @OneToOne
    private Brigade brigade;

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
