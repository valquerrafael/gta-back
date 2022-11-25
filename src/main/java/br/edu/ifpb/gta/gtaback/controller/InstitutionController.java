package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.model.Institution;
import br.edu.ifpb.gta.gtaback.model.User;
import br.edu.ifpb.gta.gtaback.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @GetMapping("/institutions")
    public List<Institution> getAll() {
        return institutionService.getAll();
    }

    @GetMapping("/institutions/{id}")
    public Institution getById(@PathVariable("id") Long id) throws Exception {
        return institutionService.getById(id);
    }

    @PostMapping("/institutions")
    public Institution create(@RequestBody Institution institution) throws Exception {
        return institutionService.create(institution);
    }

    @PutMapping("/institutions/{id}")
    public Institution update(@PathVariable("id") Long id, @RequestBody Institution institution) throws Exception {
        return institutionService.update(id, institution);
    }

    @PutMapping("/institutions/{id}/add-teacher")
    public Institution addTeacher(@PathVariable("id") Long id, @RequestBody User teacher) throws Exception {
        return institutionService.addTeacher(id, teacher);
    }

    @PutMapping("/institutions/{id}/remove-teacher")
    public Institution removeTeacher(@PathVariable("id") Long id, @RequestBody User teacher) throws Exception {
        return institutionService.removeTeacher(id, teacher);
    }

    @PutMapping("/institutions/{id}/add-student")
    public Institution addStudent(@PathVariable("id") Long id, @RequestBody User student) throws Exception {
        return institutionService.addStudent(id, student);
    }

    @PutMapping("/institutions/{id}/remove-student")
    public Institution removeStudent(@PathVariable("id") Long id, @RequestBody User student) throws Exception {
        return institutionService.removeStudent(id, student);
    }

    @DeleteMapping("/institutions/{id}")
    public void delete(@PathVariable("id") Long id) {
        institutionService.delete(id);
    }
}
