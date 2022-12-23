package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.DTO.*;
import br.edu.ifpb.gta.gtaback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/api/students/login")
    public StudentDTO login(@RequestBody StudentDTO studentDTO) {
        return studentService.login(studentDTO);
    }

    @GetMapping("/api/students/{id}")
    public StudentDTO getStudent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/api/students/get-by-cpf/{cpf}")
    public StudentDTO getStudentByCpf(@PathVariable("cpf") String cpf) {
        return studentService.getStudentByCpf(cpf);
    }

    @GetMapping("/api/students/get-all")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
    @DeleteMapping("/api/students/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/api/students")
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @PutMapping("/api/students/{id}/change-password")
    public StudentDTO updatePassword(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) {
        return studentService.updatePassword(id, studentDTO);
    }

    @PutMapping("/api/students/{id}/add-score")
    public StudentDTO addScore(@PathVariable("id") Long id, @RequestBody TrailContentDTO trailContentDTO) {
        return studentService.updateScore(id, trailContentDTO);
    }

    @PutMapping("/api/students/{id}/subscribe-trail")
    public StudentDTO subscribeTrail(@PathVariable("id") Long id, @RequestBody TrailDTO trailDTO) {
        return studentService.subscribeTrail(id, trailDTO);
    }

    @PutMapping("/api/students/{id}/remove-trail")
    public StudentDTO unsubscribeTrail(@PathVariable("id") Long id, @RequestBody TrailDTO trailDTO) {
        return studentService.unsubscribeTrail(id, trailDTO);
    }

    @GetMapping("/api/students/{id}/trails")
    public List<TrailDTO> getTrails(@PathVariable("id") Long id) {
        return studentService.getTrails(id);
    }

    @GetMapping("/api/students/ranking")
    public List<StudentDTO> getRanking() {
        return studentService.getRanking();
    }
}
