package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.DTOs.TrailDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static br.edu.ifpb.gta.gtaback.Util.trail;
import static br.edu.ifpb.gta.gtaback.Util.assertTrailDTO;
import static org.mockito.Mockito.*;

class TrailServiceTest {
    private TrailService trailServiceSpy = spy(mock(TrailService.class));

    @Test
    @DisplayName("Should get all trails")
    void getAll() {
        doReturn(new ArrayList<>() {{add(new TrailDTO(trail));}})
                .when(trailServiceSpy)
                .getAll();

        List<TrailDTO> trails = trailServiceSpy.getAll();
        TrailDTO trailDTO = trails.get(0);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should get a trail")
    void getDTO() {
        doReturn(new TrailDTO(trail))
                .when(trailServiceSpy)
                .getDTO(trail.getId());

        TrailDTO trailDTO = trailServiceSpy.getDTO(1L);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should create a new trail")
    void create() {
        doReturn(new TrailDTO(trail))
                .when(trailServiceSpy)
                .create(trail);

        TrailDTO trailDTO = trailServiceSpy.create(trail);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should update a trail")
    void update() {
        doReturn(new TrailDTO(trail))
                .when(trailServiceSpy)
                .update(trail.getId(), trail);

        TrailDTO trailDTO = trailServiceSpy.update(1L, trail);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should add a student to a trail")
    void addStudent() {
        doReturn(new TrailDTO(trail))
                .when(trailServiceSpy)
                .addStudent(trail.getId(), trail.getStudents().get(0).getId());

        TrailDTO trailDTO = trailServiceSpy.addStudent(1L, 1L);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should remove a student from a trail")
    void removeStudent() {
        doReturn(new TrailDTO(trail))
                .when(trailServiceSpy)
                .removeStudent(trail.getId(), trail.getStudents().get(0).getId());

        TrailDTO trailDTO = trailServiceSpy.removeStudent(1L, 1L);
        assertTrailDTO(trailDTO);
    }

    @Test
    @DisplayName("Should delete a trail")
    void delete() {
        trailServiceSpy.delete(trail.getId());

        verify(trailServiceSpy, times(1)).delete(trail.getId());
    }
}