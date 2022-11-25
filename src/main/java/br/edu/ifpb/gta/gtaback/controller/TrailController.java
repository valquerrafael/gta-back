package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.model.User;
import br.edu.ifpb.gta.gtaback.services.TrailService;
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
public class TrailController {
    @Autowired
    private TrailService trailService;

    @GetMapping("/trails")
    public List<Trail> getAll() {
        return trailService.getAll();
    }

    @GetMapping("/trails/{id}")
    public Trail getById(@PathVariable("id") Long id) throws Exception {
        return trailService.getById(id);
    }

    @PostMapping("/trails")
    public Trail create(@RequestBody Trail trail) throws Exception {
        return trailService.create(trail);
    }

    @PutMapping("/trails/{id}")
    public Trail update(@PathVariable("id") Long id, @RequestBody Trail trail) throws Exception {
        return trailService.update(id, trail);
    }

    @PutMapping("/trails/{id}/add-student")
    public Trail addStudent(@PathVariable("id") Long id, @RequestBody User student) throws Exception {
        return trailService.addStudent(id, student);
    }

    @PutMapping("/trails/{id}/remove-student")
    public Trail removeStudent(@PathVariable("id") Long id, @RequestBody User student) throws Exception {
        return trailService.removeStudent(id, student);
    }

    @DeleteMapping("/trails/{id}")
    public void delete(@PathVariable("id") Long id) {
        trailService.delete(id);
    }
}
