package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.model.Institution;
import br.edu.ifpb.gta.gtaback.model.User;
import br.edu.ifpb.gta.gtaback.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public Institution getById(@PathVariable("id") Long id) {
        try {
            return institutionService.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found", e);
        }
    }

    @PostMapping("/institutions")
    public Institution create(@RequestBody Institution institution) {
        try {
            return institutionService.create(institution);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Institution not created", e);
        }
    }

    @PutMapping("/institutions/{id}")
    public Institution update(@PathVariable("id") Long id, @RequestBody Institution institution) {
        try {
            return institutionService.update(id, institution);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Institution not updated", e);
        }
    }

    @PutMapping("/institutions/{id}/add-teacher")
    public Institution addTeacher(@PathVariable("id") Long id, @RequestBody User teacher) {
        try {
            return institutionService.addTeacher(id, teacher);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Teacher not added to institution", e);
        }
    }

    @PutMapping("/institutions/{id}/remove-teacher")
    public Institution removeTeacher(@PathVariable("id") Long id, @RequestBody User teacher) {
        try {
            return institutionService.removeTeacher(id, teacher);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Teacher not removed from institution", e);
        }
    }

    @PutMapping("/institutions/{id}/add-student")
    public Institution addStudent(@PathVariable("id") Long id, @RequestBody User student) {
        try {
            return institutionService.addStudent(id, student);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student not added to institution", e);
        }
    }

    @PutMapping("/institutions/{id}/remove-student")
    public Institution removeStudent(@PathVariable("id") Long id, @RequestBody User student) {
        try {
            return institutionService.removeStudent(id, student);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student not removed from institution", e);
        }
    }

    @DeleteMapping("/institutions/{id}")
    public void delete(@PathVariable("id") Long id) {
        institutionService.delete(id);
    }
}
