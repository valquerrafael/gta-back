package br.edu.ifpb.gta.gtaback.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trail")
public class Trail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(length = 3000)
    private String content;
    @ManyToOne
    private User teacher;
    @OneToMany(mappedBy = "trails")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getTeacher() {
        return teacher;
    }

    public List<User> getStudents() {
        return students;
    }

    public void addStudent(User student) {
        students.add(student);
    }

    public void removeStudent(User student) {
        students.remove(student);
    }
}
