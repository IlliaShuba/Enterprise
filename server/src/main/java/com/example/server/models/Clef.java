package com.example.server.models;
import javax.persistence.*;

@Entity
public class Clef {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String login;

    private Integer access_right;
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Integer getAccess_right() {
        return access_right;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAccess_right(Integer access_right) {
        this.access_right = access_right;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
