package br.edu.ifpb.gta.gtaback.DTO;

import br.edu.ifpb.gta.gtaback.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDTO {
    private Long teacherId;
    private String cpf;
    private String name;
    private String password;
    private List<Long> trailsIds;

    public TeacherDTO() {}

    public TeacherDTO(Teacher teacher) {
        teacherId = teacher.getId();
        cpf = teacher.getCpf();
        name = teacher.getName();
        password = teacher.getPassword();
        trailsIds = new ArrayList<>();
        if (teacher.getTrails() != null) {
            teacher.getTrails().forEach(trail -> trailsIds.add(trail.getId()));
        }
    }

    public Long getTeacherId() {
        return teacherId;
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

    public List<Long> getTrailsIds() {
        return trailsIds;
    }
}
