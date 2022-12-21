package br.edu.ifpb.gta.gtaback.model;

import br.edu.ifpb.gta.gtaback.DTO.StudentDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student implements User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String cpf;
    private String name;
    private String password;
    private Integer score = 0;
    @ManyToMany
    private List<Trail> trails;

    public Student(StudentDTO studentDTO) {
        this.id = studentDTO.getId();
        this.cpf = studentDTO.getCpf();
        this.name = studentDTO.getName();
        this.password = studentDTO.getPassword();
        this.score = studentDTO.getScore();
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getScore() {
        return score;
    }

    public void addScore(Integer score) {
        this.score += score;
    }

    public List<Trail> getTrails() {
        return trails;
    }

    public void addTrail(Trail trail) {
        this.trails.add(trail);
    }

    public void removeTrail(Trail trail) {
        this.trails.remove(trail);
    }
}
