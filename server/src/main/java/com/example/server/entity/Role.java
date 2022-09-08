package com.example.server.entity;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String accessRight;


    public Integer getId() {
        return id;
    }

    public String getAccessRight() {
        return accessRight;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccessRight(String accessRight) {
        this.accessRight = accessRight;
    }


}
