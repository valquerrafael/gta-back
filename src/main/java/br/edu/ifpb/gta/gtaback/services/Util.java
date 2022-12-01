package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.model.*;
import br.edu.ifpb.gta.gtaback.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

public class Util {
    @Autowired
    private static UserRepository userRepository;
    @Autowired
    private static TrailRepository trailRepository;
    @Autowired
    private static InstitutionRepository institutionRepository;

    static boolean doesUserExist(User user) {
        return userRepository.findById(user.getId()).isPresent();
    }

    static boolean isUserValid(User user, boolean isNew) throws Exception {
        if (user == null)
            throw new Exception("User is null");
        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank())
            throw new Exception("User name is empty");
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().isBlank())
            throw new Exception("User email is empty");
        if (user.getRole() == null)
            throw new Exception("User role is null");
        if (isNew && userRepository.findByEmail(user.getEmail()) != null)
                throw new Exception("Email already in use: " + user.getEmail());

        return true;
    }

    static boolean isUserRole(User user, Role role) throws Exception {
        if (user.getRole().equals(role))
            return true;

        throw new Exception("User is not a " + role);
    }

    static boolean doesTrailExist(Trail trail) {
        return trailRepository.findById(trail.getId()).isPresent();
    }

    static boolean isTrailValid(Trail trail, boolean isNew) throws Exception {
        if (trail == null)
            throw new Exception("Trail is null");
        if (trail.getName() == null || trail.getName().isEmpty() || trail.getName().isBlank())
            throw new Exception("Trail name is empty");
        if (trail.getDescription() == null || trail.getDescription().isEmpty() || trail.getDescription().isBlank())
            throw new Exception("Trail description is empty");
        if (trail.getContent() == null || trail.getContent().isEmpty() || trail.getContent().isBlank())
            throw new Exception("Trail content is empty");
        if (trail.getTeacher() == null)
            throw new Exception("Trail teacher is null");
        if (isNew && trailRepository.findByName(trail.getName()) != null)
            throw new Exception("Trail name already in use: " + trail.getName());

        return true;
    }

    static boolean isInstitutionValid(Institution institution, boolean isNew) throws Exception {
        if (institution == null)
            throw new Exception("Institution is null");
        if (institution.getName() == null || institution.getName().isEmpty() || institution.getName().isBlank())
            throw new Exception("Institution name is empty");
        if (isNew && institutionRepository.findByName(institution.getName()) != null)
            throw new Exception("Institution name already in use: " + institution.getName());

        return true;
    }
    
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
}
