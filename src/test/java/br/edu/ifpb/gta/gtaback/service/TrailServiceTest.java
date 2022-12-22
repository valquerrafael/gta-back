package br.edu.ifpb.gta.gtaback.service;

import br.edu.ifpb.gta.gtaback.DTO.StudentDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailContentDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailDTO;
import br.edu.ifpb.gta.gtaback.model.Student;
import br.edu.ifpb.gta.gtaback.model.Teacher;
import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.model.TrailContent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static br.edu.ifpb.gta.gtaback.service.Util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrailServiceTest {
    private TrailService trailService = mock(TrailService.class);
    private Trail mockedTrail = mockTrail();
    private TrailDTO mockedTrailDTO = mockTrailDTO();
    private StudentDTO mockedStudentDTO = mockStudentDTO();
    private Teacher mockedTeacher = mockTeacher();
    private TrailContentDTO mockedTrailContentDTO = mockTrailContentDTO();

    @Test
    @DisplayName("Should return a TrailDTO when get a trail by id")
    void getTrail() {
        when(trailService.getTrail(mockedTrailDTO.getTrailId())).thenReturn(new TrailDTO(mockedTrail));

        TrailDTO trailDTO = trailService.getTrail(mockedTrailDTO.getTrailId());

        assertEquals(mockedTrailDTO.getName(), trailDTO.getName());
        assertEquals(mockedTrailDTO.getDescription(), trailDTO.getDescription());
        assertEquals(mockedTrailDTO.getTeacherId(), trailDTO.getTeacherId());
    }

    @Test
    @DisplayName("Should return a TrailDTO when create a trail content")
    void addContent() {
        Trail newTrail = new Trail(mockedTrailDTO, mockedTeacher);
        newTrail.addContent(new TrailContent(mockedTrailContentDTO, newTrail));

        when(trailService.addContent(mockedTrailDTO.getTrailId(), mockedTrailContentDTO))
                .thenReturn(new TrailDTO(newTrail));

        TrailDTO trailDTO = trailService.addContent(mockedTrailDTO.getTrailId(), mockedTrailContentDTO);

        assertEquals(mockedTrailDTO.getName(), trailDTO.getName());
        assertEquals(mockedTrailDTO.getDescription(), trailDTO.getDescription());
        assertEquals(mockedTrailDTO.getTeacherId(), trailDTO.getTeacherId());
        assertEquals(mockedTrailDTO.getStudentsIds().size(), trailDTO.getStudentsIds().size());
        assertNotEquals(mockedTrailDTO.getContents().size(), trailDTO.getContents().size());
        assertEquals(mockedTrailDTO.getContents().size() + 1, trailDTO.getContents().size());
    }

    @Test
    @DisplayName("Should return a TrailDTO when remove a trail content")
    void removeContent() {
        Trail newTrail = new Trail(mockedTrailDTO, mockedTeacher);
        TrailContent trailContent = new TrailContent(mockedTrailContentDTO, newTrail);
        newTrail.addContent(trailContent);
        newTrail.removeContent(trailContent);

        when(trailService.removeContent(mockedTrailDTO.getTrailId(), mockedTrailContentDTO))
                .thenReturn(new TrailDTO(newTrail));

        TrailDTO trailDTO = trailService.removeContent(mockedTrailDTO.getTrailId(), mockedTrailContentDTO);

        assertEquals(mockedTrailDTO.getName(), trailDTO.getName());
        assertEquals(mockedTrailDTO.getDescription(), trailDTO.getDescription());
        assertEquals(mockedTrailDTO.getTeacherId(), trailDTO.getTeacherId());
        assertEquals(mockedTrailDTO.getStudentsIds().size(), trailDTO.getStudentsIds().size());
        assertNotEquals(mockedTrailDTO.getContents().size() + 1, trailDTO.getContents().size());
        assertEquals(mockedTrailDTO.getContents().size(), trailDTO.getContents().size());
    }

    @Test
    @DisplayName("Should return a list of StudentDTO when get all students of a trail")
    void getStudents() {
        Trail trail = new Trail(mockedTrailDTO, mockedTeacher);
        trail.addStudent(new Student(mockedStudentDTO));

        List<StudentDTO> students = new ArrayList<>();
        trail.getStudents().forEach(student -> students.add(new StudentDTO(student)));

        when(trailService.getStudents(mockedTrailDTO.getTrailId()))
                .thenReturn(students);

        List<StudentDTO> studentsDTOs = trailService.getStudents(mockedTrailDTO.getTrailId());

        assertEquals(students.size(), studentsDTOs.size());
        assertEquals(students.get(0).getStudentId(), studentsDTOs.get(0).getStudentId());
        assertEquals(students.get(0).getCpf(), studentsDTOs.get(0).getCpf());
        assertEquals(students.get(0).getName(), studentsDTOs.get(0).getName());
        assertEquals(students.get(0).getPassword(), studentsDTOs.get(0).getPassword());
        assertEquals(students.get(0).getScore(), studentsDTOs.get(0).getScore());
    }
}