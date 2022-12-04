package br.edu.ifpb.gta.gtaback.DTOs;

import br.edu.ifpb.gta.gtaback.models.Institution;

import java.util.ArrayList;
import java.util.List;

public class InstitutionDTO {
    private Long id;
    private String name;
    private String cnpj;
    private List<Long> teachersIds = new ArrayList<>();
    private List<Long> studentsIds = new ArrayList<>();

    private List<Long> trailsIds = new ArrayList<>();

    public InstitutionDTO(Institution institution) {
        this.id = institution.getId();
        this.name = institution.getName();
        this.cnpj = institution.getCnpj();
        if (institution.getTeachers() != null) {
            institution.getTeachers().forEach(teacher -> teachersIds.add(teacher.getId()));
            institution.getTeachers().forEach(teacher -> {
                if (teacher.getTrails() != null)
                    teacher.getTrails().forEach(trail -> trailsIds.add(trail.getId()));
            });
        }
        if (institution.getStudents() != null)
            institution.getStudents().forEach(student -> studentsIds.add(student.getId()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public List<Long> getTeachersIds() {
        return teachersIds;
    }

    public List<Long> getStudentsIds() {
        return studentsIds;
    }

    public List<Long> getTrailsIds() {
        return trailsIds;
    }
}
