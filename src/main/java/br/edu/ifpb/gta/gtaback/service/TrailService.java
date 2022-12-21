package br.edu.ifpb.gta.gtaback.service;

import br.edu.ifpb.gta.gtaback.DTO.StudentDTO;
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
    private TrailContentRepository trailContentRepository;

    private Trail getTrailById(Long id) {
        return trailRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Trail not found with id: " + id)
        );
    }

    public TrailDTO getTrail(Long id) {
        return new TrailDTO(getTrailById(id));
    }

    @Transactional
    public TrailDTO addContent(Long id, TrailContentDTO trailContentDTO) {
        Trail trail = getTrailById(id);
        trailContentRepository.saveAndFlush(new TrailContent(trailContentDTO, trail));
        return new TrailDTO(getTrailById(id));
    }

    @Transactional
    public TrailDTO removeContent(Long id, TrailContentDTO trailContentDTO) {
        Trail trail = getTrailById(id);
        TrailContent trailContent = trailContentRepository.findById(trailContentDTO.getTrailContentId()).orElseThrow(
            () -> new RuntimeException("Trail content not found with id: " + trailContentDTO.getTrailContentId())
        );
        trail.removeContent(trailContent);
        trailContentRepository.delete(trailContent);
        return new TrailDTO(trailRepository.save(trail));
    }

    public List<StudentDTO> getStudents(Long id) {
        List<StudentDTO> students = new ArrayList<>();
        trailRepository.findStudents(id).forEach(student -> students.add(new StudentDTO(student)));
        return students;
    }
}
