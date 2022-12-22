package br.edu.ifpb.gta.gtaback.DTO;

import br.edu.ifpb.gta.gtaback.model.TrailContent;

public class TrailContentDTO {
    private Long trailContentId;
    private String name;
    private String content;
    private Integer score;
    private Long trailId;

    public TrailContentDTO() {}

    public TrailContentDTO(TrailContent trailContent) {
        trailContentId = trailContent.getId();
        name = trailContent.getName();
        content = trailContent.getContent();
        score = trailContent.getScore();
        trailId = trailContent.getTrail().getId();
    }

    public Long getTrailContentId() {
        return trailContentId;
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

    public Long getTrailId() {
        return trailId;
    }
}
