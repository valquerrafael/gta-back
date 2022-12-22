package br.edu.ifpb.gta.gtaback.DTO;

import br.edu.ifpb.gta.gtaback.model.Trail;

import java.util.ArrayList;
import java.util.List;

public class TrailDTO {
    private Long trailId;
    private String name;
    private String description;
    private List<TrailContentDTO> contents;
    private Long teacherId;
    private List<Long> studentsIds;

    public TrailDTO() {}

    public TrailDTO(Trail trail) {
        trailId = trail.getId();
        name = trail.getName();
        description = trail.getDescription();
        contents = new ArrayList<>();
        if (trail.getContents() != null) {
            trail.getContents().forEach(content -> contents.add(new TrailContentDTO(content)));
        }
        teacherId = trail.getTeacher().getId();
        studentsIds = new ArrayList<>();
        if (trail.getStudents() != null) {
            trail.getStudents().forEach(student -> studentsIds.add(student.getId()));
        }
    }

    public Long getTrailId() {
        return trailId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<TrailContentDTO> getContents() {
        return contents;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public List<Long> getStudentsIds() {
        return studentsIds;
    }
}
