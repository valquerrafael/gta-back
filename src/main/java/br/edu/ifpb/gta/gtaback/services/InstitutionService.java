package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.Role;
import br.edu.ifpb.gta.gtaback.model.Institution;
import br.edu.ifpb.gta.gtaback.model.User;
import br.edu.ifpb.gta.gtaback.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.edu.ifpb.gta.gtaback.services.UserService.isUserRole;

@Service
public class InstitutionService {
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private UserService userService;

    public List<Institution> getAll() {
        return institutionRepository.findAll();
    }

    public Institution getById(Long id) throws Exception {
        return institutionRepository.findById(id).orElseThrow(
            () -> new Exception("Institution not found with id: " + id)
        );
    }

    @Transactional
    public Institution create(Institution institution) throws Exception {
        if (isInstitutionValid(institution, true))
            return institutionRepository.save(institution);

        return null;
    }

    @Transactional
    public Institution update(Long id, Institution institution) throws Exception {
        Institution institutionToUpdate = getById(id);

        if (isInstitutionValid(institution, false)) {
            institutionToUpdate.setName(institution.getName());
            return institutionRepository.save(institutionToUpdate);
        }

        return null;
    }

    @Transactional
    public Institution addTeacher(Long id, User teacher) throws Exception {
        Institution institution = getById(id);
        teacher = userService.getById(teacher.getId());

        if (isUserRole(teacher, Role.TEACHER) && !institution.getTeachers().contains(teacher)) {
            institution.getTeachers().add(teacher);
            return institutionRepository.save(institution);
        }

        throw new Exception("User is already in institution: " + institution.getName());
    }

    @Transactional
    public Institution removeTeacher(Long id, User teacher) throws Exception {
        Institution institution = getById(id);

        if (isUserRole(teacher, Role.TEACHER) && institution.getTeachers().contains(teacher)) {
            institution.getTeachers().remove(teacher);
            return institutionRepository.save(institution);
        }

        throw new Exception("User is not in institution: " + institution.getName());
    }

    @Transactional
    public Institution addStudent(Long id, User student) throws Exception {
        Institution institution = getById(id);
        student = userService.getById(student.getId());

        if (isUserRole(student, Role.STUDENT) && !institution.getTeachers().contains(student)) {
            institution.getTeachers().add(student);
            return institutionRepository.save(institution);
        }

        throw new Exception("User is already in institution: " + institution.getName());
    }

    @Transactional
    public Institution removeStudent(Long id, User student) throws Exception {
        Institution institution = getById(id);

        if (isUserRole(student, Role.STUDENT) && institution.getTeachers().contains(student)) {
            institution.getTeachers().remove(student);
            return institutionRepository.save(institution);
        }

        throw new Exception("User is not in institution: " + institution.getName());
    }

    @Transactional
    public void delete(Long id) {
        institutionRepository.deleteById(id);
    }

    private boolean isInstitutionValid(Institution institution, boolean isNew) throws Exception {
        boolean isValid = (
            institution != null
            && institution.getName() != null
            && !institution.getName().isEmpty()
            && !institution.getName().isBlank()
        );

        if (isValid) {
            if (isNew)
                return !isNameInUse(institution.getName());

            return true;
        }

        throw new Exception("Institution data not valid: " + institution);
    }

    private boolean isNameInUse(String name) throws Exception {
        if (institutionRepository.findByName(name) == null)
            return false;

        throw new Exception("Institution name already in use: " + name);
    }
}
