package br.edu.ifpb.gta.gtaback.controllers;

import br.edu.ifpb.gta.gtaback.DTOs.InstitutionDTO;
import br.edu.ifpb.gta.gtaback.DTOs.TrailDTO;
import br.edu.ifpb.gta.gtaback.DTOs.UserDTO;
import br.edu.ifpb.gta.gtaback.models.Institution;
import br.edu.ifpb.gta.gtaback.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institutions")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @PostMapping("/login")
    public InstitutionDTO login(@RequestBody String cnpj, String password) {
        return institutionService.login(cnpj, password);
    }

    @GetMapping("/{id}")
    public InstitutionDTO get(@PathVariable("id") Long id) {
        return institutionService.getDTO(id);
    }

    @PostMapping
    public InstitutionDTO create(@RequestBody Institution institution) {
        return institutionService.create(institution);
    }

    @PutMapping("/{id}")
    public InstitutionDTO update(@PathVariable("id") Long id, @RequestBody Institution institution) {
        return institutionService.update(id, institution);
    }

    @GetMapping("/{id}/teachers")
    public List<UserDTO> getTeachers(@PathVariable("id") Long id) {
        return institutionService.getTeachers(id);
    }

    @GetMapping("/{id}/students")
    public List<UserDTO> getStudents(@PathVariable("id") Long id) {
        return institutionService.getStudents(id);
    }

    @GetMapping("/{id}/trails")
    public List<TrailDTO> getTrails(@PathVariable("id") Long id) {
        return institutionService.getTrails(id);
    }

    @PostMapping("/{id}/add-teacher")
    public InstitutionDTO addTeacher(@PathVariable("id") Long id, @RequestBody Long teacherId) {
        return institutionService.addTeacher(id, teacherId);
    }

    @PostMapping("/{id}/remove-teacher")
    public InstitutionDTO removeTeacher(@PathVariable("id") Long id, @RequestBody Long teacherId) {
        return institutionService.removeTeacher(id, teacherId);
    }

    @PostMapping("/{id}/add-student")
    public InstitutionDTO addStudent(@PathVariable("id") Long id, @RequestBody Long studentId) {
        return institutionService.addStudent(id, studentId);
    }

    @PostMapping("/{id}/remove-student")
    public InstitutionDTO removeStudent(@PathVariable("id") Long id, @RequestBody Long studentId) {
        return institutionService.removeStudent(id, studentId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        institutionService.delete(id);
    }
}
