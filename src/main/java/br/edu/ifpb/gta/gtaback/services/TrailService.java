package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.Role;
import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.model.User;
import br.edu.ifpb.gta.gtaback.repositories.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.edu.ifpb.gta.gtaback.services.UserService.isUserRole;

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
        // verify whether teacher exists

        if (isTrailValid(trail, false) && isUserRole(trail.getTeacher(), Role.TEACHER)) {
            trailToUpdate.setName(trail.getName());
            trailToUpdate.setDescription(trail.getDescription());
            trailToUpdate.setTeacher(trail.getTeacher());
            return trailRepository.save(trailToUpdate);
        }

        return null;
    }

    @Transactional
    public Trail addStudent(Long id, User student) throws Exception {
        Trail trail = getById(id);
        // verify whether student exists

        if (isUserRole(student, Role.STUDENT) && !trail.getStudents().contains(student)) {
            trail.getStudents().add(student);
            return trailRepository.save(trail);
        }

        throw new Exception("User already in trail: " + trail.getName());
    }

    @Transactional
    public Trail removeStudent(Long id, User student) throws Exception {
        Trail trail = getById(id);

        if (isUserRole(student, Role.STUDENT) && trail.getStudents().contains(student)) {
            trail.getStudents().remove(student);
            return trailRepository.save(trail);
        }

        throw new Exception("User not in trail: " + trail.getName());
    }

    @Transactional
    public void delete(Long id) {
        trailRepository.deleteById(id);
    }

    private boolean isTrailValid(Trail trail, boolean isNew) throws Exception {
        boolean isValid = (
            trail != null
            && trail.getName() != null
            && !trail.getName().isEmpty()
            && !trail.getName().isBlank()
            && trail.getDescription() != null
            && !trail.getDescription().isEmpty()
            && !trail.getDescription().isBlank()
            && trail.getTeacher() != null
        );

        if (isValid) {
            if (isNew)
                return !isNameInUse(trail.getName());

            return true;
        }

        throw new Exception("Trail data not valid: " + trail);
    }

    private boolean isNameInUse(String name) throws Exception {
        if (trailRepository.findByName(name) == null)
            return false;

        throw new Exception("Trail already exists with name: " + name);
    }
}
