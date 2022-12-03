package br.edu.ifpb.gta.gtaback.DTOs;

import br.edu.ifpb.gta.gtaback.models.Trail;
import br.edu.ifpb.gta.gtaback.models.User;

import java.util.ArrayList;
import java.util.List;

public class TrailDTO {
    private Long id;
    private String name;
    private String description;
    private String content;
    private Long teacherId;
    private List<Long> studentsIds;

    public TrailDTO(Trail trail) {
        this.id = trail.getId();
        this.name = trail.getName();
        this.description = trail.getDescription();
        this.content = trail.getContent();
        this.teacherId = trail.getTeacher().getId();
        trail.getStudents().forEach(student -> studentsIds.add(student.getId()));
    }
}
