package br.edu.ifpb.gta.gtaback.services;

import br.edu.ifpb.gta.gtaback.DTOs.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.edu.ifpb.gta.gtaback.Util.assertStudentDTO;
import static br.edu.ifpb.gta.gtaback.Util.student;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserService userServiceSpy = spy(mock(UserService.class));

    @Test
    @DisplayName("Should succeed login")
    void login() {
        doReturn(new UserDTO(student))
            .when(userServiceSpy)
            .login(student.getEmail(), student.getPassword());

        UserDTO userDTO = userServiceSpy.login(
            "test.student@email.com",
            "123456"
        );
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should get a user DTO")
    void getDTO() {
        doReturn(new UserDTO(student))
            .when(userServiceSpy)
            .getDTO(student.getId());

        UserDTO userDTO = userServiceSpy.getDTO(1L);
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should create a new user")
    void create() {
        doReturn(new UserDTO(student))
            .when(userServiceSpy)
            .create(student);

        UserDTO userDTO = userServiceSpy.create(student);
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should update a user")
    void update() {
        doReturn(new UserDTO(student))
            .when(userServiceSpy)
            .update(student.getId(), student);

        UserDTO userDTO = userServiceSpy.update(1L, student);
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should add a trail to a user")
    void addTrail() {
        doReturn(new UserDTO(student))
            .when(userServiceSpy)
            .addTrail(student.getId(), student.getTrails().get(0).getId());

        UserDTO userDTO = userServiceSpy.addTrail(1L, 1L);
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should remove a trail from a user")
    void removeTrail() {
        doReturn(new UserDTO(student))
            .when(userServiceSpy)
            .removeTrail(student.getId(), student.getTrails().get(0).getId());

        UserDTO userDTO = userServiceSpy.removeTrail(1L, 1L);
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should delete a user")
    void delete() {
        userServiceSpy.delete(1L);

        verify(userServiceSpy, times(1)).delete(1L);
    }
}