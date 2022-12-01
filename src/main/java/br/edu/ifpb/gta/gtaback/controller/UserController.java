package br.edu.ifpb.gta.gtaback.controller;

import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.model.User;
import br.edu.ifpb.gta.gtaback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public User getById(@PathVariable("id") Long id) {
        try {
            return userService.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @PostMapping("/users")
    public User create(@RequestBody User user) {
        try {
            return userService.create(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not created", e);
        }
    }

    @PutMapping("/users/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            return userService.update(id, user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not updated", e);
        }
    }

    @PostMapping("/users/{id}/add-trail")
    public User addTrail(@PathVariable("id") Long id, @RequestBody Trail trail) {
        try {
            return userService.addTrail(id, trail);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trail not added to user", e);
        }
    }

    @PostMapping("/users/{id}/remove-trail")
    public User removeTrail(@PathVariable("id") Long id, @RequestBody Trail trail) {
        try {
            return userService.removeTrail(id, trail);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trail not removed from user", e);
        }
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
