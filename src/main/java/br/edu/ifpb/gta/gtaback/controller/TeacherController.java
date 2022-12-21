package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.DTO.TeacherDTO;
import br.edu.ifpb.gta.gtaback.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/{id}")
    public TeacherDTO getTeacher(@PathVariable("id") Long id) {
        return teacherService.getTeacher(id);
    }

    @GetMapping("/get-by-cpf/{cpf}")
    public TeacherDTO getTeacherByCpf(@PathVariable("cpf") String cpf) {
        return teacherService.getTeacherByCpf(cpf);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
    }

    @PostMapping
    public TeacherDTO createTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.createTeacher(teacherDTO);
    }

    @PutMapping("/{id}/change-password")
    public TeacherDTO updatePassword(@PathVariable("id") Long id, @RequestBody String password) {
        return teacherService.updatePassword(id, password);
    }
    
    @PutMapping("/{id}/add-trail")
    public TeacherDTO addTrail(@PathVariable("id") Long id, @RequestBody Long trailId) {
        return teacherService.addTrail(id, trailId);
    }

    @PutMapping("/{id}/remove-trail")
    public TeacherDTO removeTrail(@PathVariable("id") Long id, @RequestBody Long trailId) {
        return teacherService.removeTrail(id, trailId);
    }
}
