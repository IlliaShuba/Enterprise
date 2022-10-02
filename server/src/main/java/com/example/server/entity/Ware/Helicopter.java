package com.example.server.entity.Ware;

import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "helicopter")
    @JsonManagedReference
    private List<Work> work = new ArrayList<>();
    @OneToMany(mappedBy = "helicopter")
    @JsonManagedReference
    private Set<Equipment> equipment = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;
    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    @JsonBackReference
    private Laboratory laboratory;
}
