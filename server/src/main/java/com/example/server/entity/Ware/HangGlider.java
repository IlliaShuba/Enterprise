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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@Table(name = "hang_glider")
public class HangGlider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer weight;
    private LocalDate startCreate;
    private LocalDate finishCreate;
    private LocalDate startTest;
    private LocalDate finishTest;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hangGlider")
    @JsonManagedReference
    private List<Work> work = new ArrayList<>();
    @OneToMany(mappedBy = "hangGlider")
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
