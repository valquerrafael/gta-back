package br.edu.ifpb.gta.gtaback.controllers;

import br.edu.ifpb.gta.gtaback.DTOs.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.edu.ifpb.gta.gtaback.Util.student;
import static br.edu.ifpb.gta.gtaback.Util.assertStudentDTO;
import static org.mockito.Mockito.*;

class UserControllerTest {
    private UserController userControllerSpy = spy(mock(UserController.class));

    @Test
    @DisplayName("Should succeed login")
    void login() {
        doReturn(new UserDTO(student))
            .when(userControllerSpy)
            .login(student.getEmail(), student.getPassword());

        UserDTO userDTO = userControllerSpy.login(
            "test.student@email.com",
            "123456"
        );
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should get a user")
    void get() {
        doReturn(new UserDTO(student))
            .when(userControllerSpy)
            .get(student.getId());

        UserDTO userDTO = userControllerSpy.get(1L);
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should create a new user")
    void create() {
        doReturn(new UserDTO(student))
            .when(userControllerSpy)
            .create(student);

        UserDTO userDTO = userControllerSpy.create(student);
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should update a user")
    void update() {
        doReturn(new UserDTO(student))
            .when(userControllerSpy)
            .update(student.getId(), student);

        UserDTO userDTO = userControllerSpy.update(1L, student);
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should add a trail to a user")
    void addTrail() {
        doReturn(new UserDTO(student))
            .when(userControllerSpy)
            .addTrail(student.getId(), student.getTrails().get(0).getId());

        UserDTO userDTO = userControllerSpy.addTrail(1L, 1L);
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should remove a trail from a user")
    void removeTrail() {
        doReturn(new UserDTO(student))
            .when(userControllerSpy)
            .removeTrail(student.getId(), student.getTrails().get(0).getId());

        UserDTO userDTO = userControllerSpy.removeTrail(1L, 1L);
        assertStudentDTO(userDTO);
    }

    @Test
    @DisplayName("Should delete a user")
    void delete() {
        userControllerSpy.delete(1L);

        verify(userControllerSpy, times(1)).delete(1L);
    }
}
