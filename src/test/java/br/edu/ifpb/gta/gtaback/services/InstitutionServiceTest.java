package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.Util;
import br.edu.ifpb.gta.gtaback.DTOs.InstitutionDTO;
import br.edu.ifpb.gta.gtaback.DTOs.TrailDTO;
import br.edu.ifpb.gta.gtaback.DTOs.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static br.edu.ifpb.gta.gtaback.Util.*;
import static org.mockito.Mockito.*;

class InstitutionServiceTest {

    private InstitutionService institutionServiceSpy = spy(mock(InstitutionService.class));

    @Test
    @DisplayName("Should succeed login")
    void login() {
        doReturn(new InstitutionDTO(institution))
                .when(institutionServiceSpy)
                .login(institution.getCnpj(), institution.getPassword());

        InstitutionDTO institutionDTO = institutionServiceSpy.login(institution.getCnpj(), institution.getPassword());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should get a institution")
    void get() {
        doReturn(new InstitutionDTO(institution))
                .when(institutionServiceSpy)
                .getDTO(institution.getId());

        InstitutionDTO institutionDTO = institutionServiceSpy.getDTO(institution.getId());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should create a institution")
    void create() {
        doReturn(new InstitutionDTO(institution))
                .when(institutionServiceSpy)
                .create(institution);

        InstitutionDTO institutionDTO = institutionServiceSpy.create(institution);
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should update a institution")
    void update() {
        doReturn(new InstitutionDTO(institution))
                .when(institutionServiceSpy)
                .update(institution.getId(), institution);

        InstitutionDTO institutionDTO = institutionServiceSpy.update(institution.getId(), institution);
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should get teachers from a institution")
    void getTeachers() {
        doReturn(new ArrayList<>() {{add(new UserDTO(teacher));}})
                .when(institutionServiceSpy)
                .getTeachers(institution.getId());

        List<UserDTO> teachers = institutionServiceSpy.getTeachers(institution.getId());
        teachers.forEach(Util::assertTeacherDTO);
    }

    @Test
    @DisplayName("Should get students from a institution")
    void getStudents() {
        doReturn(new ArrayList<>() {{add(new UserDTO(student));}})
                .when(institutionServiceSpy)
                .getStudents(institution.getId());

        List<UserDTO> students = institutionServiceSpy.getStudents(institution.getId());
        students.forEach(Util::assertStudentDTO);
    }

    @Test
    @DisplayName("Should get trails from a institution")
    void getTrails() {
        doReturn(new ArrayList<>() {{add(new TrailDTO(trail));}})
                .when(institutionServiceSpy)
                .getTrails(institution.getId());

        List<TrailDTO> trails = institutionServiceSpy.getTrails(institution.getId());
        trails.forEach(Util::assertTrailDTO);
    }

    @Test
    @DisplayName("Should add a teacher to a institution")
    void addTeacher() {
        doReturn(new InstitutionDTO(institution))
                .when(institutionServiceSpy)
                .addTeacher(institution.getId(), teacher.getId());

        InstitutionDTO institutionDTO = institutionServiceSpy.addTeacher(institution.getId(), teacher.getId());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should remove a teacher from a institution")
    void removeTeacher() {
        doReturn(new InstitutionDTO(institution))
                .when(institutionServiceSpy)
                .removeTeacher(institution.getId(), teacher.getId());

        InstitutionDTO institutionDTO = institutionServiceSpy.removeTeacher(institution.getId(), teacher.getId());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should add a student to a institution")
    void addStudent() {
        doReturn(new InstitutionDTO(institution))
                .when(institutionServiceSpy)
                .addStudent(institution.getId(), student.getId());

        InstitutionDTO institutionDTO = institutionServiceSpy.addStudent(institution.getId(), student.getId());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should remove a student from a institution")
    void removeStudent() {
        doReturn(new InstitutionDTO(institution))
                .when(institutionServiceSpy)
                .removeStudent(institution.getId(), student.getId());

        InstitutionDTO institutionDTO = institutionServiceSpy.removeStudent(institution.getId(), student.getId());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should delete a institution")
    void delete() {
        institutionServiceSpy.delete(institution.getId());

        verify(institutionServiceSpy, times(1)).delete(institution.getId());
    }
}