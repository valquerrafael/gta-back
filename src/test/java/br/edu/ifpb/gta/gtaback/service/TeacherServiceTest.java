package br.edu.ifpb.gta.gtaback.service;

import br.edu.ifpb.gta.gtaback.DTO.TeacherDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailContentDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailDTO;
import br.edu.ifpb.gta.gtaback.model.Teacher;
import br.edu.ifpb.gta.gtaback.model.Trail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static br.edu.ifpb.gta.gtaback.service.Util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherServiceTest {
    private TeacherService teacherService = mock(TeacherService.class);
    private Teacher mockedTeacher = mockTeacher();
    private TeacherDTO mockedTeacherDTO = mockTeacherDTO();
    private TrailDTO mockedTrailDTO = mockTrailDTO();

    @Test
    @DisplayName("Should return a TeacherDTO when login with correct credentials")
    void login() {
        when(teacherService.login(mockedTeacherDTO)).thenReturn(new TeacherDTO(mockedTeacher));

        TeacherDTO teacherDTO = teacherService.login(mockedTeacherDTO);

        assertEquals(mockedTeacherDTO.getName(), teacherDTO.getName());
        assertEquals(mockedTeacherDTO.getCpf(), teacherDTO.getCpf());
        assertEquals(mockedTeacherDTO.getPassword(), teacherDTO.getPassword());
    }

    @Test
    @DisplayName("Should return a TeacherDTO when get a teacher by id")
    void getTeacher() {
        when(teacherService.getTeacher(mockedTeacherDTO.getTeacherId())).thenReturn(new TeacherDTO(mockedTeacher));

        TeacherDTO teacherDTO = teacherService.getTeacher(mockedTeacherDTO.getTeacherId());

        assertEquals(mockedTeacherDTO.getName(), teacherDTO.getName());
        assertEquals(mockedTeacherDTO.getCpf(), teacherDTO.getCpf());
        assertEquals(mockedTeacherDTO.getPassword(), teacherDTO.getPassword());
    }

    @Test
    @DisplayName("Should return a TeacherDTO when get a teacher by cpf")
    void getTeacherByCpf() {
        when(teacherService.getTeacherByCpf(mockedTeacherDTO.getCpf())).thenReturn(new TeacherDTO(mockedTeacher));

        TeacherDTO teacherDTO = teacherService.getTeacherByCpf(mockedTeacherDTO.getCpf());

        assertEquals(mockedTeacherDTO.getName(), teacherDTO.getName());
        assertEquals(mockedTeacherDTO.getCpf(), teacherDTO.getCpf());
        assertEquals(mockedTeacherDTO.getPassword(), teacherDTO.getPassword());
    }

    @Test
    @DisplayName("Should return a TeacherDTO when create a teacher")
    void createTeacher() {
        when(teacherService.createTeacher(mockedTeacherDTO)).thenReturn(new TeacherDTO(mockedTeacher));

        TeacherDTO teacherDTO = teacherService.createTeacher(mockedTeacherDTO);

        assertEquals(mockedTeacherDTO.getName(), teacherDTO.getName());
        assertEquals(mockedTeacherDTO.getCpf(), teacherDTO.getCpf());
        assertEquals(mockedTeacherDTO.getPassword(), teacherDTO.getPassword());
    }

    @Test
    @DisplayName("Should return a TeacherDTO when update teacher's password")
    void updatePassword() {
        String newPassword = "newPassword";
        Teacher newTeacher = new Teacher(mockedTeacherDTO);
        newTeacher.setPassword(newPassword);
        TeacherDTO newTeacherDTO = new TeacherDTO(newTeacher);

        when(teacherService.updatePassword(mockedTeacherDTO.getTeacherId(), newTeacherDTO)).thenReturn(newTeacherDTO);

        TeacherDTO teacherDTO = teacherService.updatePassword(mockedTeacherDTO.getTeacherId(), newTeacherDTO);

        assertEquals(mockedTeacherDTO.getName(), teacherDTO.getName());
        assertEquals(mockedTeacherDTO.getCpf(), teacherDTO.getCpf());
        assertNotEquals(mockedTeacherDTO.getPassword(), teacherDTO.getPassword());
        assertEquals(newPassword, teacherDTO.getPassword());
    }

    @Test
    @DisplayName("Should return a TeacherDTO when create a trail")
    void createTrail() {
        Teacher newTeacher = new Teacher(mockedTeacherDTO);
        newTeacher.addTrail(new Trail(mockedTrailDTO, mockTeacher()));
        when(teacherService.createTrail(mockedTeacherDTO.getTeacherId(), mockedTrailDTO))
                .thenReturn(new TeacherDTO(newTeacher));

        TeacherDTO teacherDTO = teacherService.createTrail(mockedTeacherDTO.getTeacherId(), mockedTrailDTO);

        assertEquals(mockedTeacherDTO.getName(), teacherDTO.getName());
        assertEquals(mockedTeacherDTO.getCpf(), teacherDTO.getCpf());
        assertEquals(mockedTeacherDTO.getPassword(), teacherDTO.getPassword());
        assertEquals(mockedTeacherDTO.getTrailsIds().size() + 1, teacherDTO.getTrailsIds().size());
    }

    @Test
    @DisplayName("Should return a TeacherDTO when delete a trail")
    void deleteTrail() {
        Teacher newTeacher = new Teacher(mockedTeacherDTO);
        Trail trail = new Trail(mockedTrailDTO, mockTeacher());
        newTeacher.addTrail(trail);
        newTeacher.removeTrail(trail);

        when(teacherService.deleteTrail(mockedTeacherDTO.getTeacherId(), mockedTrailDTO))
                .thenReturn(new TeacherDTO(newTeacher));

        TeacherDTO teacherDTO = teacherService.deleteTrail(mockedTeacherDTO.getTeacherId(), mockedTrailDTO);

        assertEquals(mockedTeacherDTO.getName(), teacherDTO.getName());
        assertEquals(mockedTeacherDTO.getCpf(), teacherDTO.getCpf());
        assertEquals(mockedTeacherDTO.getPassword(), teacherDTO.getPassword());
        assertNotEquals(mockedTeacherDTO.getTrailsIds().size() + 1, teacherDTO.getTrailsIds().size());
        assertEquals(mockedTeacherDTO.getTrailsIds().size(), teacherDTO.getTrailsIds().size());
    }

    @Test
    @DisplayName("Should return a list of TrailDTO when get all trails of a teacher")
    void getTrails() {
        Teacher newTeacher = new Teacher(mockedTeacherDTO);
        Trail trail = new Trail(mockedTrailDTO, mockTeacher());
        newTeacher.addTrail(trail);

        List<TrailDTO> trails = new ArrayList<>();
        newTeacher.getTrails().forEach(trailTeacher -> trails.add(new TrailDTO(trailTeacher)));

        when(teacherService.getTrails(mockedTeacherDTO.getTeacherId())).thenReturn(trails);

        List<TrailDTO> trailDTOList = teacherService.getTrails(mockedTeacherDTO.getTeacherId());

        assertEquals(newTeacher.getTrails().size(), trailDTOList.size());
        assertEquals(newTeacher.getTrails().get(0).getName(), trailDTOList.get(0).getName());
        assertEquals(newTeacher.getTrails().get(0).getTeacher().getId(), trailDTOList.get(0).getTeacherId());
        assertEquals(newTeacher.getTrails().get(0).getId(), trailDTOList.get(0).getTrailId());
    }
}
