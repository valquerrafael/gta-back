package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.DTOs.TrailDTO;
import br.edu.ifpb.gta.gtaback.exceptions.CreateOrUpdateDataException;
import br.edu.ifpb.gta.gtaback.exceptions.TrailHasUserException;
import br.edu.ifpb.gta.gtaback.exceptions.UserIsNotRoleException;
import br.edu.ifpb.gta.gtaback.models.Trail;
import br.edu.ifpb.gta.gtaback.models.User;
import br.edu.ifpb.gta.gtaback.repositories.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static br.edu.ifpb.gta.gtaback.services.Util.*;

@Service
public class TrailService {
    // TODO: adicionar usuário que está chamando o método
    // TODO: verificar se usuário tem permissão
    // TODO: verificar quais dados não podem ser retornados
    // TODO: criptografar dados necessários
    @Autowired
    private TrailRepository trailRepository;

    public List<TrailDTO> getAll() {
        List<TrailDTO> trails = new ArrayList<>();

        for (Trail trail : trailRepository.findAll())
            trails.add(new TrailDTO(trail));

        return trails;
    }

    public TrailDTO getDTO(Long id) {
        return new TrailDTO(getTrail(id));
    }

    public TrailDTO create(Trail trail) {
        isTrailValid(trail, true);

        trailRepository.save(trail);
        return new TrailDTO(trail);
    }

    @Transactional
    public TrailDTO update(Long id, Trail trail) {
        Trail trailToUpdate = getTrail(id);

        isTrailValid(trail, false);

        if (trail.getName() != null)
            trailToUpdate.setName(trail.getName());
        if (trail.getDescription() != null)
            trailToUpdate.setDescription(trail.getDescription());
        if (trail.getContent() != null)
            trailToUpdate.setContent(trail.getContent());
        trailRepository.save(trailToUpdate);
        return new TrailDTO(trailToUpdate);
    }

    @Transactional
    public TrailDTO addStudent(Long id, Long studentId) {
        Trail trail = getTrail(id);
        User student = getUser(studentId);

        if (!student.getRole().equals(Role.STUDENT))
            throw new UserIsNotRoleException("New trail's student is not a student");
        if (trail.getStudents().contains(student))
            throw new TrailHasUserException("Trail already has user: " + student.getId());

        trail.addStudent(student);
        trailRepository.save(trail);
        return new TrailDTO(trail);
    }

    @Transactional
    public TrailDTO removeStudent(Long id, Long studentId) {
        Trail trail = getTrail(id);
        User student = getUser(studentId);

        if (!trail.getStudents().contains(student))
            throw new TrailHasUserException("Trail does not have user: " + student.getId());

        trail.removeStudent(student);
        trailRepository.save(trail);
        return new TrailDTO(trail);
    }

    @Transactional
    public void delete(Long id) {
        trailRepository.deleteById(id);
    }

    private void isTrailValid(Trail trail, boolean isNew) {
        if (trail == null)
            throw new CreateOrUpdateDataException("Trail is null");

        if (isNew)
            isTrailValidToCreate(trail);
        else
            isTrailValidToUpdate(trail);
    }

    private void isTrailValidToCreate(Trail trail) {
        if (trail.getName() == null || trail.getName().isEmpty() || trail.getName().isBlank())
            throw new CreateOrUpdateDataException("Name is null or empty");
        if (trail.getDescription() == null || trail.getDescription().isEmpty() || trail.getDescription().isBlank())
            throw new CreateOrUpdateDataException("Description is null or empty");
        if (trail.getContent() == null || trail.getContent().isEmpty() || trail.getContent().isBlank())
            throw new CreateOrUpdateDataException("Content is null or empty");
        if (trail.getTeacher() == null)
            throw new CreateOrUpdateDataException("Teacher is null");
        if (trailRepository.findByName(trail.getName()) != null)
            throw new CreateOrUpdateDataException("Name already in use: " + trail.getName());
    }

    private void isTrailValidToUpdate(Trail trail) {
        if (trail.getName().isEmpty() || trail.getName().isBlank())
            throw new CreateOrUpdateDataException("Name is empty");
        if (trail.getDescription().isEmpty() || trail.getDescription().isBlank())
            throw new CreateOrUpdateDataException("Description is empty");
        if (trail.getContent().isEmpty() || trail.getContent().isBlank())
            throw new CreateOrUpdateDataException("Content is empty");
        Trail trailToUpdate = getTrail(trail.getId());
        if (!trailRepository.findByName(trail.getName()).getId().equals(trailToUpdate.getId()))
            throw new CreateOrUpdateDataException("Name already in use: " + trail.getName());
    }
}
