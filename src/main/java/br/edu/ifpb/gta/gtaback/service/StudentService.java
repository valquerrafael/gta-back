package br.edu.ifpb.gta.gtaback.service;

import br.edu.ifpb.gta.gtaback.DTO.StudentDTO;
import br.edu.ifpb.gta.gtaback.model.Student;
import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.repository.StudentRepository;
import br.edu.ifpb.gta.gtaback.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TrailRepository trailRepository;

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
    public StudentDTO updatePassword(Long id, String password) {
        Student student = getStudentById(id);
        student.setPassword(password);
        return new StudentDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentDTO updateScore(Long id, Integer score) {
        Student student = getStudentById(id);
        student.addScore(score);
        return new StudentDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentDTO addTrail(Long id, Long trailId) {
        Student student = getStudentById(id);
        Trail trail = trailRepository.findById(trailId).orElseThrow(
            () -> new RuntimeException("Trail not found with id: " + trailId)
        );
        student.addTrail(trail);
        return new StudentDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentDTO removeTrail(Long id, Long trailId) {
        Student student = getStudentById(id);
        Trail trail = trailRepository.findById(trailId).orElseThrow(
            () -> new RuntimeException("Trail not found with id: " + trailId)
        );
        student.removeTrail(trail);
        return new StudentDTO(studentRepository.save(student));
    }
}
