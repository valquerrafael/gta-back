package br.edu.ifpb.gta.gtaback.service;

import br.edu.ifpb.gta.gtaback.DTO.*;
import br.edu.ifpb.gta.gtaback.model.Student;
import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.repository.StudentRepository;
import br.edu.ifpb.gta.gtaback.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TrailRepository trailRepository;

    public StudentDTO login(StudentDTO studentDTO) {
        Student student = studentRepository.findByCpf(studentDTO.getCpf());
        if (student == null) {
            throw new RuntimeException("Student not found with cpf: " + studentDTO.getCpf());
        } else if (student.getPassword().equals(studentDTO.getPassword())) {
            return new StudentDTO(student);
        }
        return null;
    }

    private Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Student not found with id: " + id)
        );
    }

    public StudentDTO getStudent(Long id) {
        return new StudentDTO(getStudentById(id));
    }

    public StudentDTO getStudentByCpf(String cpf) {
        return new StudentDTO(studentRepository.findByCpf(cpf));
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        return new StudentDTO(studentRepository.save(new Student(studentDTO)));
    }

    @Transactional
    public StudentDTO updatePassword(Long id, StudentDTO studentDTO) {
        Student student = getStudentById(id);
        student.setPassword(studentDTO.getPassword());
        return new StudentDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentDTO updateScore(Long id, TrailContentDTO trailContentDTO) {
        Student student = getStudentById(id);
        student.addScore(trailContentDTO.getScore());
        return new StudentDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentDTO subscribeTrail(Long id, TrailDTO trailDTO) {
        Student student = getStudentById(id);
        Trail trail = trailRepository.findById(trailDTO.getTrailId()).orElseThrow(
            () -> new RuntimeException("Trail not found with id: " + trailDTO.getTrailId())
        );
        student.addTrail(trail);
        trail.addStudent(student);
        trailRepository.save(trail);
        return new StudentDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentDTO unsubscribeTrail(Long id, TrailDTO trailDTO) {
        Student student = getStudentById(id);
        Trail trail = trailRepository.findById(trailDTO.getTrailId()).orElseThrow(
            () -> new RuntimeException("Trail not found with id: " + trailDTO.getTrailId())
        );
        student.removeTrail(trail);
        trail.removeStudent(student);
        trailRepository.save(trail);
        return new StudentDTO(studentRepository.save(student));
    }

    public List<TrailDTO> getTrails(Long id) {
        List<TrailDTO> trails = new ArrayList<>();
        studentRepository.findTrails(id).forEach(trail -> trails.add(new TrailDTO(trail)));
        return trails;
    }

    public List<StudentDTO> getRanking() {
        List<StudentDTO> students = new ArrayList<>();
        studentRepository.findRanking().forEach(student -> students.add(new StudentDTO(student)));
        return students;
    }

    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> students = new ArrayList<>();
        studentRepository.findAll().forEach(student -> students.add(new StudentDTO(student)));
        return students;
    }
}
