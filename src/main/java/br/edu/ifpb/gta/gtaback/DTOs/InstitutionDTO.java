package br.edu.ifpb.gta.gtaback.DTOs;

import br.edu.ifpb.gta.gtaback.models.Institution;

import java.util.List;

public class InstitutionDTO {
    private Long id;
    private String name;
    private String cnpj;
    private List<Long> teachersIds;
    private List<Long> studentsIds;

    public InstitutionDTO(Institution institution) {
        this.id = institution.getId();
        this.name = institution.getName();
        this.cnpj = institution.getCnpj();
        institution.getTeachers().forEach(teacher -> teachersIds.add(teacher.getId()));
        institution.getStudents().forEach(student -> studentsIds.add(student.getId()));
    }
}
