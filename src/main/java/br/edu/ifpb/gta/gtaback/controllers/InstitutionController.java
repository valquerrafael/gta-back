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
@RequestMapping("/api")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @PostMapping("/institutions/login")
    public InstitutionDTO login(@RequestBody String cnpj, String password) {
        return institutionService.login(cnpj, password);
    }

    @GetMapping("/institutions/{id}")
    public InstitutionDTO get(@PathVariable("id") Long id) {
        return institutionService.getDTO(id);
    }

    @PostMapping("/institutions")
    public InstitutionDTO create(@RequestBody Institution institution) {
        return institutionService.create(institution);
    }

    @PutMapping("/institutions/{id}")
    public InstitutionDTO update(@PathVariable("id") Long id, @RequestBody Institution institution) {
        return institutionService.update(id, institution);
    }

    @GetMapping("/institutions/{id}/teachers")
    public List<UserDTO> getTeachers(@PathVariable("id") Long id) {
        return institutionService.getTeachers(id);
    }

    @GetMapping("/institutions/{id}/students")
    public List<UserDTO> getStudents(@PathVariable("id") Long id) {
        return institutionService.getStudents(id);
    }

    @GetMapping("/institutions/{id}/trails")
    public List<TrailDTO> getTrails(@PathVariable("id") Long id) {
        return institutionService.getTrails(id);
    }

    @PutMapping("/institutions/{id}/add-teacher")
    public InstitutionDTO addTeacher(@PathVariable("id") Long id, @RequestBody Long teacherId) {
        return institutionService.addTeacher(id, teacherId);
    }

    @PutMapping("/institutions/{id}/remove-teacher")
    public InstitutionDTO removeTeacher(@PathVariable("id") Long id, @RequestBody Long teacherId) {
        return institutionService.removeTeacher(id, teacherId);
    }

    @PutMapping("/institutions/{id}/add-student")
    public InstitutionDTO addStudent(@PathVariable("id") Long id, @RequestBody Long studentId) {
        return institutionService.addStudent(id, studentId);
    }

    @PutMapping("/institutions/{id}/remove-student")
    public InstitutionDTO removeStudent(@PathVariable("id") Long id, @RequestBody Long studentId) {
        return institutionService.removeStudent(id, studentId);
    }

    @DeleteMapping("/institutions/{id}")
    public void delete(@PathVariable("id") Long id) {
        institutionService.delete(id);
    }
}
