package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.DTO.StudentDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailContentDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailDTO;
import br.edu.ifpb.gta.gtaback.service.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrailController {
    @Autowired
    private TrailService trailService;

    @GetMapping("/api/trails/{id}")
    public TrailDTO getTrail(@PathVariable("id") Long id) {
        return trailService.getTrail(id);
    }

    @PutMapping("/api/trails/{id}/create-content")
    public TrailDTO addContent(@PathVariable("id") Long id, @RequestBody TrailContentDTO trailContentDTO) {
        return trailService.addContent(id, trailContentDTO);
    }

    @PutMapping("/api/trails/{id}/delete-content")
    public TrailDTO removeContent(@PathVariable("id") Long id, @RequestBody TrailContentDTO trailContentDTO) {
        return trailService.removeContent(id, trailContentDTO);
    }

    @GetMapping("/api/trails/{id}/students")
    public List<StudentDTO> getStudents(@PathVariable("id") Long id) {
        return trailService.getStudents(id);
    }
}
