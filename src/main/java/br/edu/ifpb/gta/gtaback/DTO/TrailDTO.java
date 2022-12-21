package br.edu.ifpb.gta.gtaback.DTO;

import br.edu.ifpb.gta.gtaback.model.Trail;

import java.util.ArrayList;
import java.util.List;

public class TrailDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> contents;
    private Long teacher;
    private List<Long> students;

    public TrailDTO(Trail trail) {
        id = trail.getId();
        name = trail.getName();
        description = trail.getDescription();
        contents = new ArrayList<>();
        trail.getContents().forEach(
            content -> contents.add(content.getId())
        );
        teacher = trail.getTeacher().getId();
        students = new ArrayList<>();
        trail.getStudents().forEach(
            student -> students.add(student.getId())
        );
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

    public List<Long> getContents() {
        return contents;
    }

    public Long getTeacher() {
        return teacher;
    }

    public List<Long> getStudents() {
        return students;
    }
}
