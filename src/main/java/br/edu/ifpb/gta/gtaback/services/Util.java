package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.exceptions.InstitutionNotFoundException;
import br.edu.ifpb.gta.gtaback.exceptions.TrailNotFoundException;
import br.edu.ifpb.gta.gtaback.exceptions.UserNotFoundException;
import br.edu.ifpb.gta.gtaback.models.*;
import br.edu.ifpb.gta.gtaback.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

public class Util {
    @Autowired
    private static UserRepository userRepository;
    @Autowired
    private static TrailRepository trailRepository;
    @Autowired
    private static InstitutionRepository institutionRepository;

    public enum Role {
        STUDENT("student"),
        TEACHER("teacher");

        private final String role;
        Role(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

    static User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException("User not found with id: " + id)
        );
    }

    static Trail getTrail(Long id) throws RuntimeException {
        return trailRepository.findById(id).orElseThrow(
            () -> new TrailNotFoundException("Trail not found with id: " + id)
        );
    }

    static Institution getInstitution(Long id) throws RuntimeException {
        return institutionRepository.findById(id).orElseThrow(
            () -> new InstitutionNotFoundException("Institution not found with id: " + id)
        );
    }
}
