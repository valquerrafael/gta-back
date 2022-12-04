package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.DTOs.UserDTO;
import br.edu.ifpb.gta.gtaback.exceptions.CreateOrUpdateDataException;
import br.edu.ifpb.gta.gtaback.exceptions.LoginFailedException;
import br.edu.ifpb.gta.gtaback.exceptions.UserHasTrailException;
import br.edu.ifpb.gta.gtaback.exceptions.UserNotFoundException;
import br.edu.ifpb.gta.gtaback.models.Trail;
import br.edu.ifpb.gta.gtaback.models.User;
import br.edu.ifpb.gta.gtaback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    // TODO: adicionar usuário que está chamando o método
    // TODO: verificar se usuário tem permissão
    // TODO: verificar quais dados não podem ser retornados
    // TODO: criptografar dados necessários
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UtilService utilService;

    public UserDTO login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null)
            throw new UserNotFoundException("User not found with email: " + email);
        if (!user.getPassword().equals(password))
            throw new LoginFailedException("Login failed");

        return new UserDTO(user);
    }

    public UserDTO getDTO(Long id) {
        return new UserDTO(utilService.getUser(id));
    }

    @Transactional
    public UserDTO create(User user) {
        isUserValid(user, true);

        userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO update(Long id, User user) {
        User userToUpdate = utilService.getUser(id);

        isUserValid(user, false);

        if (user.getName() != null)
            userToUpdate.setName(user.getName());
        if (user.getEmail() != null)
            userToUpdate.setEmail(user.getEmail());
        if (user.getPassword() != null)
            userToUpdate.setPassword(user.getPassword());
        userRepository.save(userToUpdate);
        return new UserDTO(userToUpdate);
    }

    @Transactional
    public UserDTO addTrail(Long id, Long trailId) {
        User user = utilService.getUser(id);
        Trail trail = utilService.getTrail(trailId);

        if (user.getTrails().contains(trail))
            throw new UserHasTrailException("User already has trail: " + trail.getName());

        user.addTrail(trail);
        userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO removeTrail(Long id, Long trailId) throws RuntimeException {
        User user = utilService.getUser(id);
        Trail trail = utilService.getTrail(trailId);

        if (!user.getTrails().contains(trail))
            throw new UserHasTrailException("User does not have trail: " + trail.getName());

        user.removeTrail(trail);
        userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private void isUserValid(User user, boolean isNew) {
        if (user == null)
            throw new CreateOrUpdateDataException("User is null");

        if (isNew)
            isUserValidToCreate(user);
        else
            isUserValidToUpdate(user);
    }

    private void isUserValidToCreate(User user) {
        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank())
            throw new CreateOrUpdateDataException("Name is null or empty");
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().isBlank())
            throw new CreateOrUpdateDataException("Email is null or empty");
        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().isBlank())
            throw new CreateOrUpdateDataException("Password is null or empty");
        if (user.getRole() == null)
            throw new CreateOrUpdateDataException("Role is null");
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new CreateOrUpdateDataException("Email already in use: " + user.getEmail());
        if (user.getInstitution() == null)
            throw new CreateOrUpdateDataException("Institution is null");
        utilService.getInstitution(user.getInstitution().getId());
    }

    private void isUserValidToUpdate(User user) {
        if (user.getName().isEmpty() || user.getName().isBlank())
            throw new CreateOrUpdateDataException("Name is empty");
        if (user.getEmail().isEmpty() || user.getEmail().isBlank())
            throw new CreateOrUpdateDataException("Email is empty");
        if (user.getPassword().isEmpty() || user.getPassword().isBlank())
            throw new CreateOrUpdateDataException("Password is empty");
        User userToUpdate = utilService.getUser(user.getId());
        if (user.getRole() != userToUpdate.getRole())
            throw new CreateOrUpdateDataException("Role is not the same");
        if (!userRepository.findByEmail(user.getEmail()).getId().equals(userToUpdate.getId()))
            throw new CreateOrUpdateDataException("Email already in use: " + user.getEmail());
    }
}
