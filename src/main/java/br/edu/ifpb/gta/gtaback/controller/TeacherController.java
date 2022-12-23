package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.DTO.*;
import br.edu.ifpb.gta.gtaback.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/api/teachers/login")
    public TeacherDTO login(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.login(teacherDTO);
    }

    @GetMapping("/api/teachers/{id}")
    public TeacherDTO getTeacher(@PathVariable("id") Long id) {
        return teacherService.getTeacher(id);
    }

    @GetMapping("/api/teachers/get-by-cpf/{cpf}")
    public TeacherDTO getTeacherByCpf(@PathVariable("cpf") String cpf) {
        return teacherService.getTeacherByCpf(cpf);
    }

    @DeleteMapping("/api/teachers/{id}")
    public void deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
    }

    @PostMapping("/api/teachers")
    public TeacherDTO createTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.createTeacher(teacherDTO);
    }

    @PutMapping("/api/teachers/{id}/change-password")
    public TeacherDTO updatePassword(@PathVariable("id") Long id, @RequestBody TeacherDTO teacherDTO) {
        return teacherService.updatePassword(id, teacherDTO);
    }

    @PutMapping("/api/teachers/{id}/create-trail")
    public TeacherDTO createTrail(@PathVariable("id") Long id, @RequestBody TrailDTO trailDTO) {
        return teacherService.createTrail(id, trailDTO);
    }

    @PutMapping("/api/teachers/{id}/delete-trail")
    public TeacherDTO deleteTrail(@PathVariable("id") Long id, @RequestBody TrailDTO trailDTO) {
        return teacherService.deleteTrail(id, trailDTO);
    }

    @GetMapping("/api/teachers/{id}/trails")
    public List<TrailDTO> getTrails(@PathVariable("id") Long id) {
        return teacherService.getTrails(id);
    }

    @GetMapping("/api/teachers/get-all")
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
}
