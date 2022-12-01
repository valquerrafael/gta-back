package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.model.User;
import br.edu.ifpb.gta.gtaback.repositories.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.edu.ifpb.gta.gtaback.services.Util.*;

@Service
public class TrailService {
    @Autowired
    private TrailRepository trailRepository;

    public List<Trail> getAll() {
        return trailRepository.findAll();
    }

    public Trail getById(Long id) throws Exception {
        return trailRepository.findById(id).orElseThrow(() -> new Exception("Trail not found with id: " + id));
    }

    public Trail create(Trail trail) throws Exception {
        if (isTrailValid(trail, true))
            return trailRepository.save(trail);

        return null;
    }

    @Transactional
    public Trail update(Long id, Trail trail) throws Exception {
        Trail trailToUpdate = getById(id);

        if (isUserRole(trail.getTeacher(), Role.TEACHER))
            throw new Exception("User is not a teacher");
        if (!doesUserExist(trail.getTeacher()))
            throw new Exception("Teacher not found with id: " + trail.getTeacher().getId());

        if (isTrailValid(trail, false)) {
            trailToUpdate.setName(trail.getName());
            trailToUpdate.setDescription(trail.getDescription());
            trailToUpdate.setContent(trail.getContent());
            trailToUpdate.setTeacher(trail.getTeacher());
            return trailRepository.save(trailToUpdate);
        }

        return null;
    }

    @Transactional
    public Trail addStudent(Long id, User student) throws Exception {
        Trail trail = getById(id);

        if (!isUserRole(student, Role.STUDENT))
            throw new Exception("User is not a student");
        if (!doesUserExist(student))
            throw new Exception("Student not found with id: " + student.getId());

        if (!trail.getStudents().contains(student)) {
            trail.getStudents().add(student);
            return trailRepository.save(trail);
        }

        throw new Exception("User already in trail: " + trail.getName());
    }

    @Transactional
    public Trail removeStudent(Long id, User student) throws Exception {
        Trail trail = getById(id);

        if (trail.getStudents().contains(student)) {
            trail.getStudents().remove(student);
            return trailRepository.save(trail);
        }

        throw new Exception("User not in trail: " + trail.getName());
    }

    @Transactional
    public void delete(Long id) {
        trailRepository.deleteById(id);
    }
}
