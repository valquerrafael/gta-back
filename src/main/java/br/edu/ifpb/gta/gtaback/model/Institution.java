package br.edu.ifpb.gta.gtaback.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "institution")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<User> teachers;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
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
}
