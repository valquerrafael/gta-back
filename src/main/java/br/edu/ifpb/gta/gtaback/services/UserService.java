package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.Role;
import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.model.User;
import br.edu.ifpb.gta.gtaback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found with id: " + id));
    }

    @Transactional
    public User create(User user) throws Exception {
        if (isUserValid(user, true))
            return userRepository.save(user);

        return null;
    }

    @Transactional
    public User update(Long id, User user) throws Exception {
        User userToUpdate = getById(id);

        if (isUserValid(user, false)) {
            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());
            return userRepository.save(userToUpdate);
        }

        return null;
    }

    @Transactional
    public User addTrail(Long id, Trail trail) throws Exception {
        User user = getById(id);
        // verify whether trail exists

        if (!user.getTrails().contains(trail)) {
            user.getTrails().add(trail);
            return userRepository.save(user);
        }

        throw new Exception("User already in trail: " + trail.getName());
    }

    @Transactional
    public User removeTrail(Long id, Trail trail) throws Exception {
        User user = getById(id);

        if (user.getTrails().contains(trail)) {
            user.getTrails().remove(trail);
            return userRepository.save(user);
        }

        throw new Exception("User not in trail: " + trail.getName());
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private boolean isUserValid(User user, boolean isNew) throws Exception {
        boolean isValid = (
            user != null
            && user.getName() != null && !user.getName().isEmpty() && !user.getName().isBlank()
            && user.getEmail() != null && !user.getEmail().isEmpty() && !user.getEmail().isBlank()
            && user.getRole() != null
        );

        if (isValid) {
            if (isNew)
                return !isEmailInUse(user.getEmail());

            return true;
        }

        throw new Exception("User data not valid: " + user);
    }

    private boolean isEmailInUse(String email) throws Exception {
        if (userRepository.findByEmail(email) == null)
            return false;

        throw new Exception("Email already in use: " + email);
    }

    static boolean isUserRole(User user, Role role) throws Exception {
        if (user.getRole().equals(role))
            return true;

        throw new Exception("User is not a " + role);
    }
}
