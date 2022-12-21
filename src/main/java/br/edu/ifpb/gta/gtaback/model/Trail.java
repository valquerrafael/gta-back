package br.edu.ifpb.gta.gtaback.model;

import br.edu.ifpb.gta.gtaback.DTO.TrailDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trail {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String name;
    @Column(length=5000)
    private String description;
    @OneToMany(mappedBy="trail", cascade=CascadeType.REMOVE)
    private List<TrailContent> contents;
    @ManyToOne
    private Teacher teacher;
    @ManyToMany
    private List<Student> students;

    public Trail(TrailDTO trailDTO, Teacher teacher) {
        this.id = trailDTO.getId();
        this.name = trailDTO.getName();
        this.description = trailDTO.getDescription();
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<TrailContent> getContents() {
        return contents;
    }

    public void addContent(TrailContent content) {
        contents.add(content);
    }

    public void removeContent(TrailContent content) {
        contents.remove(content);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }
}
