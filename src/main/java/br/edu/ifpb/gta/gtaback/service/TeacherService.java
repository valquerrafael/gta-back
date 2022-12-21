package br.edu.ifpb.gta.gtaback.service;

import br.edu.ifpb.gta.gtaback.DTO.TeacherDTO;
import br.edu.ifpb.gta.gtaback.model.Teacher;
import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.repository.TeacherRepository;
import br.edu.ifpb.gta.gtaback.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TrailRepository trailRepository;

    private Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Teacher not found with id: " + id)
        );
    }

    public TeacherDTO getTeacher(Long id) {
        return new TeacherDTO(getTeacherById(id));
    }

    public TeacherDTO getTeacherByCpf(String cpf) {
        return new TeacherDTO(teacherRepository.findByCpf(cpf));
    }

    @Transactional
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Transactional
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        return new TeacherDTO(teacherRepository.save(new Teacher(teacherDTO)));
    }

    @Transactional
    public TeacherDTO updatePassword(Long id, String password) {
        Teacher teacher = getTeacherById(id);
        teacher.setPassword(password);
        return new TeacherDTO(teacherRepository.save(teacher));
    }

    @Transactional
    public TeacherDTO addTrail(Long id, Long trailId) {
        Teacher teacher = getTeacherById(id);
        Trail trail = trailRepository.findById(trailId).orElseThrow(
            () -> new RuntimeException("Trail not found with id: " + trailId)
        );
        teacher.addTrail(trail);
        return new TeacherDTO(teacherRepository.save(teacher));
    }

    @Transactional
    public TeacherDTO removeTrail(Long id, Long trailId) {
        Teacher teacher = getTeacherById(id);
        Trail trail = trailRepository.findById(trailId).orElseThrow(
            () -> new RuntimeException("Trail not found with id: " + trailId)
        );
        teacher.removeTrail(trail);
        return new TeacherDTO(teacherRepository.save(teacher));
    }
}
