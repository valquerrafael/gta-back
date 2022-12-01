package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.model.User;
import br.edu.ifpb.gta.gtaback.services.TrailService;
import org.apache.coyote.Response;
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
public class TrailController {
    @Autowired
    private TrailService trailService;

    @GetMapping("/trails")
    public List<Trail> getAll() {
        return trailService.getAll();
    }

    @GetMapping("/trails/{id}")
    public Trail getById(@PathVariable("id") Long id) {
        try {
            return trailService.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trail not found", e);
        }
    }

    @PostMapping("/trails")
    public Trail create(@RequestBody Trail trail) {
        try {
            return trailService.create(trail);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trail not created", e);
        }
    }

    @PutMapping("/trails/{id}")
    public Trail update(@PathVariable("id") Long id, @RequestBody Trail trail) {
        try {
            return trailService.update(id, trail);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trail not updated", e);
        }
    }

    @PutMapping("/trails/{id}/add-student")
    public Trail addStudent(@PathVariable("id") Long id, @RequestBody User student) {
        try {
            return trailService.addStudent(id, student);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student not added to trail", e);
        }
    }

    @PutMapping("/trails/{id}/remove-student")
    public Trail removeStudent(@PathVariable("id") Long id, @RequestBody User student) {
        try {
            return trailService.removeStudent(id, student);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student not removed from trail", e);
        }
    }

    @DeleteMapping("/trails/{id}")
    public void delete(@PathVariable("id") Long id) {
        trailService.delete(id);
    }
}
