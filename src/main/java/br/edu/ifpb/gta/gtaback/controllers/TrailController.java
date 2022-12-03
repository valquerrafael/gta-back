package br.edu.ifpb.gta.gtaback.controllers;

import br.edu.ifpb.gta.gtaback.DTOs.TrailDTO;
import br.edu.ifpb.gta.gtaback.models.Trail;
import br.edu.ifpb.gta.gtaback.services.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrailController {
    @Autowired
    private TrailService trailService;

    @GetMapping("/trails")
    public List<TrailDTO> getAll() {
        return trailService.getAll();
    }

    @GetMapping("/trails/{id}")
    public TrailDTO get(@PathVariable("id") Long id) {
        return trailService.getDTO(id);
    }

    @PostMapping("/trails")
    public TrailDTO create(@RequestBody Trail trail) {
        return trailService.create(trail);
    }

    @PutMapping("/trails/{id}")
    public TrailDTO update(@PathVariable("id") Long id, @RequestBody Trail trail) {
        return trailService.update(id, trail);
    }

    @PostMapping("/trails/{id}/add-student")
    public TrailDTO addStudent(@PathVariable("id") Long id, @RequestBody Long studentId) {
        return trailService.addStudent(id, studentId);
    }

    @PostMapping("/trails/{id}/remove-student")
    public TrailDTO removeStudent(@PathVariable("id") Long id, @RequestBody Long studentId) {
        return trailService.removeStudent(id, studentId);
    }

    @DeleteMapping("/trails/{id}")
    public void delete(@PathVariable("id") Long id) {
        trailService.delete(id);
    }
}
