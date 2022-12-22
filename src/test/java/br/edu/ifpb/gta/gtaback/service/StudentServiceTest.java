package br.edu.ifpb.gta.gtaback.service;

import br.edu.ifpb.gta.gtaback.DTO.StudentDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailContentDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailDTO;
import br.edu.ifpb.gta.gtaback.model.Student;
import br.edu.ifpb.gta.gtaback.model.Trail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static br.edu.ifpb.gta.gtaback.service.Util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    private StudentService studentService = mock(StudentService.class);
    private Student mockedStudent = mockStudent();
    private StudentDTO mockedStudentDTO = mockStudentDTO();
    private TrailDTO mockedTrailDTO = mockTrailDTO();
    private TrailContentDTO mockedTrailContentDTO = mockTrailContentDTO();

    @Test
    @DisplayName("Should return a StudentDTO when login with correct credentials")
    void login() {
        when(studentService.login(mockedStudentDTO)).thenReturn(new StudentDTO(mockedStudent));

        StudentDTO studentDTO = studentService.login(mockedStudentDTO);

        assertEquals(mockedStudentDTO.getName(), studentDTO.getName());
        assertEquals(mockedStudentDTO.getCpf(), studentDTO.getCpf());
        assertEquals(mockedStudentDTO.getPassword(), studentDTO.getPassword());
        assertEquals(mockedStudentDTO.getScore(), studentDTO.getScore());
    }

    @Test
    @DisplayName("Should return a StudentDTO when get a student by id")
    void getStudent() {
        when(studentService.getStudent(mockedStudentDTO.getStudentId())).thenReturn(new StudentDTO(mockedStudent));

        StudentDTO studentDTO = studentService.getStudent(mockedStudentDTO.getStudentId());

        assertEquals(mockedStudentDTO.getName(), studentDTO.getName());
        assertEquals(mockedStudentDTO.getCpf(), studentDTO.getCpf());
        assertEquals(mockedStudentDTO.getPassword(), studentDTO.getPassword());
        assertEquals(mockedStudentDTO.getScore(), studentDTO.getScore());
    }

    @Test
    @DisplayName("Should return a StudentDTO when get a student by cpf")
    void getStudentByCpf() {
        when(studentService.getStudentByCpf(mockedStudentDTO.getCpf())).thenReturn(new StudentDTO(mockedStudent));

        StudentDTO studentDTO = studentService.getStudentByCpf(mockedStudentDTO.getCpf());

        assertEquals(mockedStudentDTO.getName(), studentDTO.getName());
        assertEquals(mockedStudentDTO.getCpf(), studentDTO.getCpf());
        assertEquals(mockedStudentDTO.getPassword(), studentDTO.getPassword());
        assertEquals(mockedStudentDTO.getScore(), studentDTO.getScore());
    }

    @Test
    @DisplayName("Should return a StudentDTO when create a student")
    void createStudent() {
        when(studentService.createStudent(mockedStudentDTO)).thenReturn(new StudentDTO(mockedStudent));

        StudentDTO studentDTO = studentService.createStudent(mockedStudentDTO);

        assertEquals(mockedStudentDTO.getName(), studentDTO.getName());
        assertEquals(mockedStudentDTO.getCpf(), studentDTO.getCpf());
        assertEquals(mockedStudentDTO.getPassword(), studentDTO.getPassword());
        assertEquals(mockedStudentDTO.getScore(), studentDTO.getScore());
    }

    @Test
    @DisplayName("Should return a StudentDTO when update student's password")
    void updatePassword() {
        String newPassword = "newPassword";
        Student newStudent = new Student(mockedStudentDTO);
        newStudent.setPassword(newPassword);
        StudentDTO newStudentDTO = new StudentDTO(newStudent);

        when(studentService.updatePassword(mockedStudentDTO.getStudentId(), newStudentDTO)).thenReturn(newStudentDTO);

        StudentDTO studentDTO = studentService.updatePassword(mockedStudentDTO.getStudentId(), newStudentDTO);

        assertEquals(mockedStudentDTO.getName(), studentDTO.getName());
        assertEquals(mockedStudentDTO.getCpf(), studentDTO.getCpf());
        assertNotEquals(mockedStudentDTO.getPassword(), studentDTO.getPassword());
        assertEquals(newPassword, studentDTO.getPassword());
        assertEquals(mockedStudentDTO.getScore(), studentDTO.getScore());
    }

    @Test
    @DisplayName("Should return a StudentDTO when update student's score")
    void updateScore() {
        Student newStudent = new Student(mockedStudentDTO);
        newStudent.addScore(mockedTrailContentDTO.getScore());

        when(studentService.updateScore(mockedStudentDTO.getStudentId(), mockedTrailContentDTO))
                .thenReturn(new StudentDTO(newStudent));

        StudentDTO studentDTO = studentService.updateScore(mockedStudentDTO.getStudentId(), mockedTrailContentDTO);

        assertEquals(mockedStudentDTO.getName(), studentDTO.getName());
        assertEquals(mockedStudentDTO.getCpf(), studentDTO.getCpf());
        assertEquals(mockedStudentDTO.getPassword(), studentDTO.getPassword());
        assertNotEquals(mockedStudentDTO.getScore(), studentDTO.getScore());
        assertEquals(mockedStudentDTO.getScore() + mockedTrailContentDTO.getScore(), studentDTO.getScore());
    }

    @Test
    @DisplayName("Should return a StudentDTO when subscribe to trail")
    void subscribeTrail() {
        Student newStudent = new Student(mockedStudentDTO);
        newStudent.addTrail(new Trail(mockedTrailDTO, mockTeacher()));
        when(studentService.subscribeTrail(mockedStudentDTO.getStudentId(), mockedTrailDTO))
                .thenReturn(new StudentDTO(newStudent));

        StudentDTO studentDTO = studentService.subscribeTrail(mockedStudentDTO.getStudentId(), mockedTrailDTO);

        assertEquals(mockedStudentDTO.getName(), studentDTO.getName());
        assertEquals(mockedStudentDTO.getCpf(), studentDTO.getCpf());
        assertEquals(mockedStudentDTO.getPassword(), studentDTO.getPassword());
        assertEquals(mockedStudentDTO.getScore(), studentDTO.getScore());
        assertEquals(mockedStudentDTO.getTrailsIds().size() + 1, studentDTO.getTrailsIds().size());
    }

    @Test
    @DisplayName("Should return a StudentDTO when unsubscribe to trail")
    void unsubscribeTrail() {
        Student newStudent = new Student(mockedStudentDTO);
        Trail trail = new Trail(mockedTrailDTO, mockTeacher());
        newStudent.addTrail(trail);
        newStudent.removeTrail(trail);

        when(studentService.unsubscribeTrail(mockedStudentDTO.getStudentId(), mockedTrailDTO))
                .thenReturn(new StudentDTO(newStudent));

        StudentDTO studentDTO = studentService.unsubscribeTrail(mockedStudentDTO.getStudentId(), mockedTrailDTO);

        assertEquals(mockedStudentDTO.getName(), studentDTO.getName());
        assertEquals(mockedStudentDTO.getCpf(), studentDTO.getCpf());
        assertEquals(mockedStudentDTO.getPassword(), studentDTO.getPassword());
        assertEquals(mockedStudentDTO.getScore(), studentDTO.getScore());
        assertNotEquals(mockedStudentDTO.getTrailsIds().size() + 1, studentDTO.getTrailsIds().size());
        assertEquals(mockedStudentDTO.getTrailsIds().size(), studentDTO.getTrailsIds().size());
    }

    @Test
    @DisplayName("Should return a list of TrailDTO when get all trails of a student")
    void getTrails() {
        Student newStudent = new Student(mockedStudentDTO);
        Trail trail = new Trail(mockedTrailDTO, mockTeacher());
        newStudent.addTrail(trail);

        List<TrailDTO> trails = new ArrayList<>();
        newStudent.getTrails().forEach(trailStudent -> trails.add(new TrailDTO(trailStudent)));

        when(studentService.getTrails(mockedStudentDTO.getStudentId())).thenReturn(trails);

        List<TrailDTO> trailDTOList = studentService.getTrails(mockedStudentDTO.getStudentId());

        assertEquals(newStudent.getTrails().size(), trailDTOList.size());
        assertEquals(newStudent.getTrails().get(0).getName(), trailDTOList.get(0).getName());
        assertEquals(newStudent.getTrails().get(0).getTeacher().getId(), trailDTOList.get(0).getTeacherId());
        assertEquals(newStudent.getTrails().get(0).getId(), trailDTOList.get(0).getTrailId());
    }

    @Test
    @DisplayName("Should return a list of StudentDTO when get ranking")
    void getRanking() {
        Student newStudent = new Student(mockedStudentDTO);
        newStudent.addScore(mockedTrailContentDTO.getScore());

        Student newStudent2 = new Student(mockStudentDTO2());
        newStudent2.addScore(mockedTrailContentDTO.getScore() * 2);

        List<StudentDTO> students = new ArrayList<>();
        students.add(new StudentDTO(newStudent));
        students.add(new StudentDTO(newStudent2));

        Collections.sort(students, new Comparator<StudentDTO>() {
            @Override
            public int compare(StudentDTO o1, StudentDTO o2) {
                return o2.getScore().compareTo(o1.getScore());
            }
        });

        when(studentService.getRanking()).thenReturn(students);

        List<StudentDTO> studentDTOList = studentService.getRanking();

        assertEquals(students.size(), studentDTOList.size());
        assertEquals(newStudent2.getName(), studentDTOList.get(0).getName());
        assertEquals(newStudent.getName(), studentDTOList.get(1).getName());
        assertEquals(newStudent2.getCpf(), studentDTOList.get(0).getCpf());
        assertEquals(newStudent.getCpf(), studentDTOList.get(1).getCpf());
        assertEquals(newStudent2.getPassword(), studentDTOList.get(0).getPassword());
        assertEquals(newStudent.getPassword(), studentDTOList.get(1).getPassword());
        assertEquals(newStudent2.getScore(), studentDTOList.get(0).getScore());
        assertEquals(newStudent.getScore(), studentDTOList.get(1).getScore());
    }
}
