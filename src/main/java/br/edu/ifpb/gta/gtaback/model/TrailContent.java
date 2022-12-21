package br.edu.ifpb.gta.gtaback.model;

import br.edu.ifpb.gta.gtaback.DTO.TrailContentDTO;

import javax.persistence.*;

@Entity
public class TrailContent {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String name;
    @Column(length=100000)
    private String content;
    private Integer score;
    @ManyToOne
    private Trail trail;

    public TrailContent() {}

    public TrailContent(TrailContentDTO trailContentDTO, Trail trail) {
        this.id = trailContentDTO.getTrailContentId();
        this.name = trailContentDTO.getName();
        this.content = trailContentDTO.getContent();
        this.score = trailContentDTO.getScore();
        this.trail = trail;
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

    public Trail getTrail() {
        return trail;
    }
}
