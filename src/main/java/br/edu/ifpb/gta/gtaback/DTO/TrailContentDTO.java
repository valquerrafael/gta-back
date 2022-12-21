package br.edu.ifpb.gta.gtaback.DTO;

import br.edu.ifpb.gta.gtaback.model.TrailContent;

public class TrailContentDTO {
    private Long id;
    private String name;
    private String content;
    private Integer score;
    private Long trail;
    private Long student;

    public TrailContentDTO(TrailContent trailContent) {
        id = trailContent.getId();
        name = trailContent.getName();
        content = trailContent.getContent();
        score = trailContent.getScore();
        trail = trailContent.getTrail().getId();
        student = trailContent.getStudent().getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Integer getScore() {
        return score;
    }

    public Long getTrail() {
        return trail;
    }

    public Long getStudent() {
        return student;
    }
}
