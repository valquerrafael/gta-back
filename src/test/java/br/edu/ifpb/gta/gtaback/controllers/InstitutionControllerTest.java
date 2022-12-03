package br.edu.ifpb.gta.gtaback.controllers;

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

class InstitutionControllerTest {

    private InstitutionController institutionControllerSpy = spy(mock(InstitutionController.class));

    @Test
    @DisplayName("Should succeed login")
    void login() {
        doReturn(new InstitutionDTO(institution))
            .when(institutionControllerSpy)
            .login(institution.getCnpj(), institution.getPassword());

        InstitutionDTO institutionDTO = institutionControllerSpy.login(institution.getCnpj(), institution.getPassword());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should get a institution")
    void get() {
        doReturn(new InstitutionDTO(institution))
            .when(institutionControllerSpy)
            .get(institution.getId());

        InstitutionDTO institutionDTO = institutionControllerSpy.get(institution.getId());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should create a institution")
    void create() {
        doReturn(new InstitutionDTO(institution))
            .when(institutionControllerSpy)
            .create(institution);

        InstitutionDTO institutionDTO = institutionControllerSpy.create(institution);
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should update a institution")
    void update() {
        doReturn(new InstitutionDTO(institution))
            .when(institutionControllerSpy)
            .update(institution.getId(), institution);

        InstitutionDTO institutionDTO = institutionControllerSpy.update(institution.getId(), institution);
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should get teachers from a institution")
    void getTeachers() {
        doReturn(new ArrayList<>() {{add(new UserDTO(teacher));}})
            .when(institutionControllerSpy)
            .getTeachers(institution.getId());

        List<UserDTO> teachers = institutionControllerSpy.getTeachers(institution.getId());
        teachers.forEach(Util::assertTeacherDTO);
    }

    @Test
    @DisplayName("Should get students from a institution")
    void getStudents() {
        doReturn(new ArrayList<>() {{add(new UserDTO(student));}})
            .when(institutionControllerSpy)
            .getStudents(institution.getId());

        List<UserDTO> students = institutionControllerSpy.getStudents(institution.getId());
        students.forEach(Util::assertStudentDTO);
    }

    @Test
    @DisplayName("Should get trails from a institution")
    void getTrails() {
        doReturn(new ArrayList<>() {{add(new TrailDTO(trail));}})
            .when(institutionControllerSpy)
            .getTrails(institution.getId());

        List<TrailDTO> trails = institutionControllerSpy.getTrails(institution.getId());
        trails.forEach(Util::assertTrailDTO);
    }

    @Test
    @DisplayName("Should add a teacher to a institution")
    void addTeacher() {
        doReturn(new InstitutionDTO(institution))
            .when(institutionControllerSpy)
            .addTeacher(institution.getId(), teacher.getId());

        InstitutionDTO institutionDTO = institutionControllerSpy.addTeacher(institution.getId(), teacher.getId());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should remove a teacher from a institution")
    void removeTeacher() {
        doReturn(new InstitutionDTO(institution))
            .when(institutionControllerSpy)
            .removeTeacher(institution.getId(), teacher.getId());

        InstitutionDTO institutionDTO = institutionControllerSpy.removeTeacher(institution.getId(), teacher.getId());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should add a student to a institution")
    void addStudent() {
        doReturn(new InstitutionDTO(institution))
            .when(institutionControllerSpy)
            .addStudent(institution.getId(), student.getId());

        InstitutionDTO institutionDTO = institutionControllerSpy.addStudent(institution.getId(), student.getId());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should remove a student from a institution")
    void removeStudent() {
        doReturn(new InstitutionDTO(institution))
            .when(institutionControllerSpy)
            .removeStudent(institution.getId(), student.getId());

        InstitutionDTO institutionDTO = institutionControllerSpy.removeStudent(institution.getId(), student.getId());
        assertInstitutionDTO(institutionDTO);
    }

    @Test
    @DisplayName("Should delete a institution")
    void delete() {
        institutionControllerSpy.delete(institution.getId());

        verify(institutionControllerSpy, times(1)).delete(institution.getId());
    }
}