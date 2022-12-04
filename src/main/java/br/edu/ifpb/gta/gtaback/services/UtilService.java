package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.exceptions.InstitutionNotFoundException;
import br.edu.ifpb.gta.gtaback.exceptions.TrailNotFoundException;
import br.edu.ifpb.gta.gtaback.exceptions.UserNotFoundException;
import br.edu.ifpb.gta.gtaback.models.*;
import br.edu.ifpb.gta.gtaback.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrailRepository trailRepository;
    @Autowired
    private InstitutionRepository institutionRepository;

    User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException("User not found with id: " + id)
        );
    }

    Trail getTrail(Long id) throws RuntimeException {
        return trailRepository.findById(id).orElseThrow(
            () -> new TrailNotFoundException("Trail not found with id: " + id)
        );
    }

    Institution getInstitution(Long id) throws RuntimeException {
        return institutionRepository.findById(id).orElseThrow(
            () -> new InstitutionNotFoundException("Institution not found with id: " + id)
        );
    }
}
