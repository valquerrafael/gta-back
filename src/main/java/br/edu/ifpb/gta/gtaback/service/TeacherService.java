package br.edu.ifpb.gta.gtaback.service;

import br.edu.ifpb.gta.gtaback.DTO.*;
import br.edu.ifpb.gta.gtaback.model.Teacher;
import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.repository.TeacherRepository;
import br.edu.ifpb.gta.gtaback.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TrailRepository trailRepository;

    public TeacherDTO login(TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findByCpf(teacherDTO.getCpf());
        if (teacher == null) {
            throw new RuntimeException("Teacher not found with cpf: " + teacherDTO.getCpf());
        } else if (teacher.getPassword().equals(teacherDTO.getPassword())) {
            return new TeacherDTO(teacher);
        }
        return null;
    }

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
    public TeacherDTO updatePassword(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = getTeacherById(id);
        teacher.setPassword(teacherDTO.getPassword());
        return new TeacherDTO(teacherRepository.save(teacher));
    }

    @Transactional
    public TeacherDTO createTrail(Long id, TrailDTO trailDTO) {
        Teacher teacher = getTeacherById(id);
        Trail trail = trailRepository.saveAndFlush(new Trail(trailDTO, teacher));
        teacher.addTrail(trail);
        return new TeacherDTO(teacherRepository.save(teacher));
    }

    @Transactional
    public TeacherDTO deleteTrail(Long id, TrailDTO trailDTO) {
        Teacher teacher = getTeacherById(id);
        Trail trail = trailRepository.findById(trailDTO.getTrailId()).orElseThrow(
            () -> new RuntimeException("Trail not found with id: " + trailDTO.getTrailId())
        );
        teacher.removeTrail(trail);
        trailRepository.delete(trail);
        return new TeacherDTO(teacherRepository.save(teacher));
    }

    public List<TrailDTO> getTrails(Long id) {
        List<TrailDTO> trails = new ArrayList<>();
        teacherRepository.findTrails(id).forEach(trail -> trails.add(new TrailDTO(trail)));
        return trails;
    }

    public List<TeacherDTO> getAllTeachers() {
        List<TeacherDTO> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teacher -> teachers.add(new TeacherDTO(teacher)));
        return teachers;
    }
}
