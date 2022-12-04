package br.edu.ifpb.gta.gtaback.controllers;

import br.edu.ifpb.gta.gtaback.DTOs.TrailDTO;
import br.edu.ifpb.gta.gtaback.models.Trail;
import br.edu.ifpb.gta.gtaback.services.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trails")
public class TrailController {
    @Autowired
    private TrailService trailService;

    @GetMapping("/{id}")
    public TrailDTO get(@PathVariable("id") Long id) {
        return trailService.getDTO(id);
    }

    @PostMapping
    public TrailDTO create(@RequestBody Trail trail) {
        return trailService.create(trail);
    }

    @PutMapping("/{id}")
    public TrailDTO update(@PathVariable("id") Long id, @RequestBody Trail trail) {
        return trailService.update(id, trail);
    }

    @PostMapping("/{id}/add-student")
    public TrailDTO addStudent(@PathVariable("id") Long id, @RequestBody Long studentId) {
        return trailService.addStudent(id, studentId);
    }

    @PostMapping("/{id}/remove-student")
    public TrailDTO removeStudent(@PathVariable("id") Long id, @RequestBody Long studentId) {
        return trailService.removeStudent(id, studentId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        trailService.delete(id);
    }
}
