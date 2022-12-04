package br.edu.ifpb.gta.gtaback.controllers;

import br.edu.ifpb.gta.gtaback.DTOs.UserDTO;
import br.edu.ifpb.gta.gtaback.models.User;
import br.edu.ifpb.gta.gtaback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDTO login(@RequestBody String email, String password) {
        return userService.login(email, password);
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable("id") Long id) {
        return userService.getDTO(id);
    }

    @PostMapping
    public UserDTO create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @PostMapping("/{id}/add-trail")
    public UserDTO addTrail(@PathVariable("id") Long id, @RequestBody Long trailId) {
        return userService.addTrail(id, trailId);
    }

    @PostMapping("/{id}/remove-trail")
    public UserDTO removeTrail(@PathVariable("id") Long id, @RequestBody Long trailId) {
        return userService.removeTrail(id, trailId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
