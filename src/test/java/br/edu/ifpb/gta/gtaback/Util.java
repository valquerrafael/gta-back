package br.edu.ifpb.gta.gtaback;

import br.edu.ifpb.gta.gtaback.DTOs.InstitutionDTO;
import br.edu.ifpb.gta.gtaback.DTOs.TrailDTO;
import br.edu.ifpb.gta.gtaback.DTOs.UserDTO;
import br.edu.ifpb.gta.gtaback.models.Institution;
import br.edu.ifpb.gta.gtaback.models.Trail;
import br.edu.ifpb.gta.gtaback.models.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Util {
    public static void assertStudentDTO(UserDTO userDTO) {
        assertEquals(userDTO.getId(), student.getId());
        assertEquals(userDTO.getName(), student.getName());
        assertEquals(userDTO.getEmail(), student.getEmail());
        assertEquals(userDTO.getRole(), student.getRole());
        assertEquals(userDTO.getInstitutionId(), student.getInstitution().getId());
        assertEquals(userDTO.getTrailsIds().get(0), student.getTrails().get(0).getId());
    }

    public static void assertTeacherDTO(UserDTO userDTO) {
        assertEquals(userDTO.getId(), teacher.getId());
        assertEquals(userDTO.getName(), teacher.getName());
        assertEquals(userDTO.getEmail(), teacher.getEmail());
        assertEquals(userDTO.getRole(), teacher.getRole());
        assertEquals(userDTO.getInstitutionId(), teacher.getInstitution().getId());
        assertEquals(userDTO.getTrailsIds().get(0), teacher.getTrails().get(0).getId());
    }

    public static void assertTrailDTO(TrailDTO trailDTO) {
        assertEquals(trailDTO.getId(), trail.getId());
        assertEquals(trailDTO.getName(), trail.getName());
        assertEquals(trailDTO.getDescription(), trail.getDescription());
        assertEquals(trailDTO.getContent(), trail.getContent());
        assertEquals(trailDTO.getTeacherId(), trail.getTeacher().getId());
        assertEquals(trailDTO.getStudentsIds().get(0), trail.getStudents().get(0).getId());
    }

    public static void assertInstitutionDTO(InstitutionDTO institutionDTO) {
        assertEquals(institutionDTO.getId(), institution.getId());
        assertEquals(institutionDTO.getName(), institution.getName());
        assertEquals(institutionDTO.getCnpj(), institution.getCnpj());
        assertEquals(institutionDTO.getTeachersIds().get(0), institution.getTeachers().get(0).getId());
        assertEquals(institutionDTO.getStudentsIds().get(0), institution.getStudents().get(0).getId());
    }
    public static User student = new User() {
        private Long id = 1L;
        private String name = "Test Student";
        private String email = "test.student@email.com";
        private String password = "123456";
        private br.edu.ifpb.gta.gtaback.services.Util.Role role = br.edu.ifpb.gta.gtaback.services.Util.Role.STUDENT;

        public Long getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getEmail() {
            return email;
        }
        public String getPassword() {
            return password;
        }
        public br.edu.ifpb.gta.gtaback.services.Util.Role getRole() {
            return role;
        }
        public Institution getInstitution() {
            return institution;
        }
        public List<Trail> getTrails() {
            return new ArrayList<>() {{add(trail);}};
        }
    };

    public static User teacher = new User() {
        private Long id = 2L;
        private String name = "Test Teacher";
        private String email = "test.teacher@email.com";
        private String password = "123456";
        private br.edu.ifpb.gta.gtaback.services.Util.Role role = br.edu.ifpb.gta.gtaback.services.Util.Role.TEACHER;

        public Long getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getEmail() {
            return email;
        }
        public String getPassword() {
            return password;
        }
        public br.edu.ifpb.gta.gtaback.services.Util.Role getRole() {
            return role;
        }
        public Institution getInstitution() {
            return institution;
        }
        public List<Trail> getTrails() {
            return new ArrayList<>() {{add(trail);}};
        }
    };

    public static Institution institution = new Institution() {
        private Long id = 1L;
        private String name = "Test Institution";
        private String cnpj = "12.345.678/0001-90";
        private String password = "123456";

        public Long getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getCnpj() {
            return cnpj;
        }
        public String getPassword() {
            return password;
        }
        public List<User> getTeachers() {
            return new ArrayList<>() {{add(teacher);}};
        }
        public List<User> getStudents() {
            return new ArrayList<>() {{add(student);}};
        }
    };

    public static Trail trail = new Trail() {
        private Long id = 1L;
        private String name = "Test Trail";
        private String description = "Test Trail Description";
        private String content = "Test Trail Content";

        public Long getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getDescription() {
            return description;
        }
        public String getContent() {
            return content;
        }
        public User getTeacher() {
            return teacher;
        }
        public List<User> getStudents() {
            return new ArrayList<>() {{add(student);}};
        }
    };
}
