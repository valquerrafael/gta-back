package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.DTOs.InstitutionDTO;
import br.edu.ifpb.gta.gtaback.DTOs.TrailDTO;
import br.edu.ifpb.gta.gtaback.DTOs.UserDTO;
import br.edu.ifpb.gta.gtaback.exceptions.*;
import br.edu.ifpb.gta.gtaback.models.Institution;
import br.edu.ifpb.gta.gtaback.models.Trail;
import br.edu.ifpb.gta.gtaback.models.User;
import br.edu.ifpb.gta.gtaback.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static br.edu.ifpb.gta.gtaback.services.Util.*;

@Service
public class InstitutionService {
    // TODO: adicionar usuário que está chamando o método
    // TODO: verificar se usuário tem permissão
    // TODO: verificar quais dados não podem ser retornados
    // TODO: criptografar dados necessários
    @Autowired
    private InstitutionRepository institutionRepository;

    public InstitutionDTO login(String cnpj, String password) {
        Institution institution = institutionRepository.findByCnpj(cnpj);

        if (institution == null)
            throw new InstitutionNotFoundException("Institution not found with cnpj: " + cnpj);
        if (!institution.getPassword().equals(password))
            throw new LoginFailedException("Login failed");

        return new InstitutionDTO(institution);
    }

    public InstitutionDTO getDTO(Long id) {
        return new InstitutionDTO(getInstitution(id));
    }

    @Transactional
    public InstitutionDTO create(Institution institution) {
        isInstitutionValid(institution, true);

        institutionRepository.save(institution);
        return new InstitutionDTO(institution);
    }

    @Transactional
    public InstitutionDTO update(Long id, Institution institution) {
        Institution institutionToUpdate = getInstitution(id);

        isInstitutionValid(institution, false);

        if (institution.getName() != null)
            institutionToUpdate.setName(institution.getName());
        if (institution.getPassword() != null)
            institutionToUpdate.setPassword(institution.getPassword());
        institutionRepository.save(institutionToUpdate);
        return new InstitutionDTO(institutionToUpdate);
    }

    public List<UserDTO> getTeachers(Long id) {
        Institution institution = getInstitution(id);
        List<UserDTO> teachers = new ArrayList<>();

        for (User user : institution.getTeachers())
            teachers.add(new UserDTO(user));

        return teachers;
    }

    public List<UserDTO> getStudents(Long id) {
        Institution institution = getInstitution(id);
        List<UserDTO> students = new ArrayList<>();

        for (User user : institution.getStudents())
            students.add(new UserDTO(user));

        return students;
    }

    public List<TrailDTO> getTrails(Long id) {
        Institution institution = getInstitution(id);
        List<TrailDTO> trails = new ArrayList<>();

        for (Trail trail : institution.getTrails())
            trails.add(new TrailDTO(trail));

        return trails;
    }

    @Transactional
    public InstitutionDTO addTeacher(Long id, Long teacherId) {
        Institution institution = getInstitution(id);
        User teacher = getUser(teacherId);

        if (!teacher.getRole().equals(Role.TEACHER))
            throw new UserIsNotRoleException("New institution's teacher is not a teacher");
        if (institution.getTeachers().contains(teacher))
            throw new TrailHasUserException("Institution already has user: " + teacher.getId());

        institution.addTeacher(teacher);
        institutionRepository.save(institution);
        return new InstitutionDTO(institution);
    }

    @Transactional
    public InstitutionDTO removeTeacher(Long id, Long teacherId) {
        Institution institution = getInstitution(id);
        User teacher = getUser(teacherId);

        if (!institution.getStudents().contains(teacher))
            throw new TrailHasUserException("Institution does not have user: " + teacher.getId());

        institution.removeTeacher(teacher);
        institutionRepository.save(institution);
        return new InstitutionDTO(institution);
    }

    @Transactional
    public InstitutionDTO addStudent(Long id, Long studentId) {
        Institution institution = getInstitution(id);
        User student = getUser(studentId);

        if (!student.getRole().equals(Role.STUDENT))
            throw new UserIsNotRoleException("New institution's student is not a student");
        if (institution.getStudents().contains(student))
            throw new TrailHasUserException("Institution already has user: " + student.getId());

        institution.addStudent(student);
        institutionRepository.save(institution);
        return new InstitutionDTO(institution);
    }

    @Transactional
    public InstitutionDTO removeStudent(Long id, Long studentId) {
        Institution institution = getInstitution(id);
        User student = getUser(studentId);

        if (!institution.getStudents().contains(student))
            throw new TrailHasUserException("Institution does not have user: " + student.getId());

        institution.removeStudent(student);
        institutionRepository.save(institution);
        return new InstitutionDTO(institution);
    }

    @Transactional
    public void delete(Long id) {
        institutionRepository.deleteById(id);
    }

    private void isInstitutionValid(Institution institution, boolean isNew) {
        if (institution == null)
            throw new CreateOrUpdateDataException("Institution is null");

        if (isNew)
            isInstitutionValidToCreate(institution);
        else
            isInstitutionValidToUpdate(institution);
    }

    private void isInstitutionValidToCreate(Institution institution) {
        if (institution.getName() == null || institution.getName().isEmpty() || institution.getName().isBlank())
            throw new CreateOrUpdateDataException("Name is null or empty");
        if (institution.getCnpj() == null || institution.getCnpj().isEmpty() || institution.getCnpj().isBlank())
            throw new CreateOrUpdateDataException("CNPJ is null or empty");
        if (institution.getPassword() == null || institution.getPassword().isEmpty() || institution.getPassword().isBlank())
            throw new CreateOrUpdateDataException("Password is empty");
        if (institutionRepository.findByName(institution.getName()) != null)
            throw new CreateOrUpdateDataException("Name already in use: " + institution.getName());
    }

    private void isInstitutionValidToUpdate(Institution institution) {
        if (institution.getName().isEmpty() || institution.getName().isBlank())
            throw new CreateOrUpdateDataException("Name is empty");
        if (institution.getPassword().isEmpty() || institution.getPassword().isBlank())
            throw new CreateOrUpdateDataException("Password is empty");
        Institution institutionToUpdate = getInstitution(institution.getId());
        if (!institutionRepository.findByName(institution.getName()).getId().equals(institutionToUpdate.getId()))
            throw new CreateOrUpdateDataException("Name already in use: " + institution.getName());
    }
}
