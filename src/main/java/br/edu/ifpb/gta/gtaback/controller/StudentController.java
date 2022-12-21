package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.DTO.StudentDTO;
import br.edu.ifpb.gta.gtaback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/get-by-cpf/{cpf}")
    public StudentDTO getStudentByCpf(@PathVariable("cpf") String cpf) {
        return studentService.getStudentByCpf(cpf);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    @PutMapping("/{id}/change-password")
    public StudentDTO updatePassword(@PathVariable("id") Long id, @RequestBody String password) {
        return studentService.updatePassword(id, password);
    }

    @PutMapping("/{id}/add-score")
    public StudentDTO addScore(@PathVariable("id") Long id, @RequestBody Integer score) {
        return studentService.updateScore(id, score);
    }

    @PutMapping("/{id}/add-trail")
    public StudentDTO addTrail(@PathVariable("id") Long id, @RequestBody Long trailId) {
        return studentService.addTrail(id, trailId);
    }

    @PutMapping("/{id}/remove-trail")
    public StudentDTO removeTrail(@PathVariable("id") Long id, @RequestBody Long trailId) {
        return studentService.removeTrail(id, trailId);
    }
}
