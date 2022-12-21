package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.DTO.TrailDTO;
import br.edu.ifpb.gta.gtaback.service.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/trails")
public class TrailController {
    @Autowired
    private TrailService trailService;

    @GetMapping("/{id}")
    public TrailDTO getTrail(@PathVariable("id") Long id) {
        return trailService.getTrail(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrail(@PathVariable("id") Long id) {
        trailService.deleteTrail(id);
    }

    @PostMapping
    public TrailDTO createTrail(@RequestBody TrailDTO trailDTO) {
        return trailService.createTrail(trailDTO);
    }

    @PutMapping("/{id}/add-content")
    public TrailDTO addContent(@PathVariable("id") Long id, @RequestBody Long contentId) {
        return trailService.addContent(id, contentId);
    }

    @PutMapping("/{id}/remove-content")
    public TrailDTO removeContent(@PathVariable("id") Long id, @RequestBody Long contentId) {
        return trailService.removeContent(id, contentId);
    }

    @PutMapping("/{id}/add-student")
    public TrailDTO addStudent(@PathVariable("id") Long id, @RequestBody Long studentId) {
        return trailService.addStudent(id, studentId);
    }

    @PutMapping("/{id}/remove-student")
    public TrailDTO removeStudent(@PathVariable("id") Long id, @RequestBody Long studentId) {
        return trailService.removeStudent(id, studentId);
    }
}
