package br.edu.ifpb.gta.gtaback.DTO;

import br.edu.ifpb.gta.gtaback.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO {
    private Long id;
    private String cpf;
    private String name;
    private String password;
    private Integer score;
    private List<Long> trails;

    public StudentDTO(Student student) {
        id = student.getId();
        cpf = student.getCpf();
        name = student.getName();
        password = student.getPassword();
        score = student.getScore();
        trails = new ArrayList<>();
        student.getTrails().forEach(
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

    public Integer getScore() {
        return score;
    }

    public List<Long> getTrails() {
        return trails;
    }
}
