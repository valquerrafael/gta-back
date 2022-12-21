package br.edu.ifpb.gta.gtaback.DTO;

import br.edu.ifpb.gta.gtaback.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDTO {
    private Long id;
    private String cpf;
    private String name;
    private String password;
    private List<Long> trails;

    public TeacherDTO(Teacher teacher) {
        id = teacher.getId();
        cpf = teacher.getCpf();
        name = teacher.getName();
        password = teacher.getPassword();
        trails = new ArrayList<>();
        teacher.getTrails().forEach(
            trail -> trails.add(trail.getId())
        );
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

    public List<Long> getTrails() {
        return trails;
    }
}
