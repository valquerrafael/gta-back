package br.edu.ifpb.gta.gtaback.controllers;

import br.edu.ifpb.gta.gtaback.DTOs.UserDTO;
import br.edu.ifpb.gta.gtaback.models.User;
import br.edu.ifpb.gta.gtaback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/login")
    public UserDTO login(@RequestBody String email, String password) {
        return userService.login(email, password);
    }

    @GetMapping("/users/{id}")
    public UserDTO get(@PathVariable("id") Long id) {
        return userService.getDTO(id);
    }

    @PostMapping("/users")
    public UserDTO create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/users/{id}")
    public UserDTO update(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @PostMapping("/users/{id}/add-trail")
    public UserDTO addTrail(@PathVariable("id") Long id, @RequestBody Long trailId) {
        return userService.addTrail(id, trailId);
    }

    @PostMapping("/users/{id}/remove-trail")
    public UserDTO removeTrail(@PathVariable("id") Long id, @RequestBody Long trailId) {
        return userService.removeTrail(id, trailId);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
