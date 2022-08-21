package com.example.server.models;

import javax.persistence.*;

@Entity
@Table(name="role",uniqueConstraints=@UniqueConstraint(columnNames={"login"}))
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String access_right;


    public Integer getId() {
        return id;
    }

    public String getAccess_right() {
        return access_right;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccess_right(String access_right) {
        this.access_right = access_right;
    }


}
