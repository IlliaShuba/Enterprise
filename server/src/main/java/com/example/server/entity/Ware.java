package com.example.server.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ware")
public class Ware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeOfWorks;
    private LocalDateTime startCreate;
    private LocalDateTime finishCreate;
    private LocalDateTime startTest;
    private LocalDateTime finishTest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeOfWorks() {
        return typeOfWorks;
    }

    public void setTypeOfWorks(String typeOfWorks) {
        this.typeOfWorks = typeOfWorks;
    }

    public LocalDateTime getStartCreate() {
        return startCreate;
    }

    public void setStartCreate(LocalDateTime startCreate) {
        this.startCreate = startCreate;
    }

    public LocalDateTime getFinishCreate() {
        return finishCreate;
    }

    public void setFinishCreate(LocalDateTime finishCreate) {
        this.finishCreate = finishCreate;
    }

    public LocalDateTime getStartTest() {
        return startTest;
    }

    public void setStartTest(LocalDateTime startTest) {
        this.startTest = startTest;
    }

    public LocalDateTime getFinishTest() {
        return finishTest;
    }

    public void setFinishTest(LocalDateTime finishTest) {
        this.finishTest = finishTest;
    }
}
