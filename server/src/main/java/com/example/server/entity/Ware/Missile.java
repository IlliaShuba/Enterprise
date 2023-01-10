package com.example.server.entity.Ware;

import com.example.server.entity.Equipment;
import com.example.server.entity.Laboratory;
import com.example.server.entity.Shop;
import com.example.server.entity.Ware.Prototype.Copyable;
import com.example.server.repository.EquipmentRepository;
import com.example.server.repository.LaboratoryRepository;
import com.example.server.repository.ShopRepository;
import com.example.server.service.EquipmentService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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
@Table(name = "missile")
public class Missile implements Copyable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer chargePower;
    private LocalDate startCreate;
    private LocalDate finishCreate;
    private LocalDate startTest;
    private LocalDate finishTest;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "missile")
    @JsonManagedReference
    private List<Work> work = new ArrayList<>();
    @OneToMany(mappedBy = "missile")
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

    @Override
    public Object copy(){

        Missile copy = new Missile();
        copy.setChargePower(this.getChargePower());
        copy.setStartCreate(LocalDate.now());
        copy.setFinishCreate(null);
        copy.setStartTest(null);
        copy.setFinishTest(null);

        /*Shop shop = shopRepository.findById(this.getShop().getId()).get();
        Laboratory laboratory = laboratoryRepository.findById(this.getLaboratory().getId()).get();
        copy.setShop(shop);
        copy.setLaboratory(laboratory);
        for (Equipment id : this.getEquipment()) {
            Equipment equipment = equipmentRepository.findById(id.getId()).get();
            equipment.setMissile(copy);
        }*/
        return copy;
    }
}
