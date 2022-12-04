package br.edu.ifpb.gta.gtaback.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "institution")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String cnpj;
    @Column
    private String password;
    @OneToMany(mappedBy = "institution")
    private List<User> teachers;
    @OneToMany(mappedBy = "institution")
    private List<User> students;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getTeachers() {
        return teachers;
    }

    public void addTeacher(User teacher) {
        this.teachers.add(teacher);
    }

    public void removeTeacher(User teacher) {
        this.teachers.remove(teacher);
    }

    public List<User> getStudents() {
        return students;
    }

    public void addStudent(User student) {
        this.students.add(student);
    }

    public void removeStudent(User student) {
        this.students.remove(student);
    }

    public List<Trail> getTrails() {
        List<Trail> trails = new ArrayList<>();

        for (User teacher : this.teachers)
            trails.addAll(teacher.getTrails());

        return trails;
    }
}
