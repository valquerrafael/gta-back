package br.edu.ifpb.gta.gtaback.model;

import br.edu.ifpb.gta.gtaback.DTO.TeacherDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher implements User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String cpf;
    private String name;
    private String password;
    @ManyToMany(cascade=CascadeType.REMOVE)
    private List<Trail> trails = new ArrayList<>();

    public Teacher() {}

    public Teacher(TeacherDTO teacherDTO) {
        this.id = teacherDTO.getTeacherId();
        this.cpf = teacherDTO.getCpf();
        this.name = teacherDTO.getName();
        this.password = teacherDTO.getPassword();
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
