package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.model.User;
import br.edu.ifpb.gta.gtaback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.edu.ifpb.gta.gtaback.services.Util.*;

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

        if (!doesTrailExist(trail))
            throw new Exception("Trail not found with id: " + trail.getId());

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
}
