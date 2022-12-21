package br.edu.ifpb.gta.gtaback.service;

import br.edu.ifpb.gta.gtaback.DTO.TrailContentDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailDTO;
import br.edu.ifpb.gta.gtaback.model.*;
import br.edu.ifpb.gta.gtaback.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrailService {
    @Autowired
    private TrailRepository trailRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TrailContentRepository trailContentRepository;

    private Trail getTrailById(Long id) {
        return trailRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Trail not found with id: " + id)
        );
    }

    public TrailDTO getTrail(Long id) {
        return new TrailDTO(getTrailById(id));
    }

    public List<TrailContentDTO> getTrailContent(Long id) {
        List<TrailContentDTO> allTrailContent = new ArrayList<>();
        trailContentRepository.findByTrailId(id).forEach(
            trailContent -> allTrailContent.add(new TrailContentDTO(trailContent))
        );
        return allTrailContent;
    }

    @Transactional
    public void deleteTrail(Long id) {
        trailRepository.deleteById(id);
    }

    @Transactional
    public TrailDTO createTrail(TrailDTO trailDTO) {
        Teacher teacher = teacherRepository.findById(trailDTO.getTeacher()).orElseThrow(
            () -> new RuntimeException("Teacher not found with id: " + trailDTO.getTeacher())
        );
        return new TrailDTO(trailRepository.save(new Trail(trailDTO, teacher)));
    }

    @Transactional
    public TrailDTO addContent(Long id, Long contentId) {
        Trail trail = getTrailById(id);
        TrailContent trailContent = trailContentRepository.findById(contentId).orElseThrow(
            () -> new RuntimeException("Trail content not found with id: " + contentId)
        );
        trail.addContent(trailContent);
        return new TrailDTO(trailRepository.save(trail));
    }

    @Transactional
    public TrailDTO removeContent(Long id, Long contentId) {
        Trail trail = getTrailById(id);
        TrailContent trailContent = trailContentRepository.findById(contentId).orElseThrow(
            () -> new RuntimeException("Trail content not found with id: " + contentId)
        );
        trail.removeContent(trailContent);
        return new TrailDTO(trailRepository.save(trail));
    }

    @Transactional
    public TrailDTO addStudent(Long id, Long studentId) {
        Trail trail = getTrailById(id);
        Student student = studentRepository.findById(studentId).orElseThrow(
            () -> new RuntimeException("Student not found with id: " + studentId)
        );
        trail.addStudent(student);
        return new TrailDTO(trailRepository.save(trail));
    }

    @Transactional
    public TrailDTO removeStudent(Long id, Long studentId) {
        Trail trail = getTrailById(id);
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new RuntimeException("Student not found with id: " + studentId)
        );
        trail.removeStudent(student);
        return new TrailDTO(trailRepository.save(trail));
    }
}
