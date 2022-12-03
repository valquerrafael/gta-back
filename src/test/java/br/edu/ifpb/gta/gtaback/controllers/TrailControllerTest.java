package br.edu.ifpb.gta.gtaback.controllers;

import br.edu.ifpb.gta.gtaback.DTOs.TrailDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static br.edu.ifpb.gta.gtaback.Util.trail;
import static br.edu.ifpb.gta.gtaback.Util.assertTrailDTO;
import static org.mockito.Mockito.*;

class TrailControllerTest {
    private TrailController trailControllerSpy = spy(mock(TrailController.class));

    @Test
    @DisplayName("Should get all trails")
    void getAll() {
        doReturn(new ArrayList<>() {{add(new TrailDTO(trail));}})
            .when(trailControllerSpy)
            .getAll();

        List<TrailDTO> trails = trailControllerSpy.getAll();
        TrailDTO trailDTO = trails.get(0);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should get a trail")
    void get() {
        doReturn(new TrailDTO(trail))
            .when(trailControllerSpy)
            .get(trail.getId());

        TrailDTO trailDTO = trailControllerSpy.get(1L);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should create a new trail")
    void create() {
        doReturn(new TrailDTO(trail))
            .when(trailControllerSpy)
            .create(trail);

        TrailDTO trailDTO = trailControllerSpy.create(trail);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should update a trail")
    void update() {
        doReturn(new TrailDTO(trail))
            .when(trailControllerSpy)
            .update(trail.getId(), trail);

        TrailDTO trailDTO = trailControllerSpy.update(1L, trail);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should add a student to a trail")
    void addStudent() {
        doReturn(new TrailDTO(trail))
            .when(trailControllerSpy)
            .addStudent(trail.getId(), trail.getStudents().get(0).getId());

        TrailDTO trailDTO = trailControllerSpy.addStudent(1L, 1L);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should remove a student from a trail")
    void removeStudent() {
        doReturn(new TrailDTO(trail))
            .when(trailControllerSpy)
            .removeStudent(trail.getId(), trail.getStudents().get(0).getId());

        TrailDTO trailDTO = trailControllerSpy.removeStudent(1L, 1L);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should delete a trail")
    void delete() {
        trailControllerSpy.delete(trail.getId());

        verify(trailControllerSpy, times(1)).delete(trail.getId());
    }
}