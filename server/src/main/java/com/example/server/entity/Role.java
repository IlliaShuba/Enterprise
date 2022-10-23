package com.example.server.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String accessRight;

}
