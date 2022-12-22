package br.edu.ifpb.gta.gtaback.service;

import br.edu.ifpb.gta.gtaback.DTO.StudentDTO;
import br.edu.ifpb.gta.gtaback.DTO.TeacherDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailContentDTO;
import br.edu.ifpb.gta.gtaback.DTO.TrailDTO;
import br.edu.ifpb.gta.gtaback.model.Student;
import br.edu.ifpb.gta.gtaback.model.Teacher;
import br.edu.ifpb.gta.gtaback.model.Trail;
import br.edu.ifpb.gta.gtaback.model.TrailContent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    
    public static Student mockStudent() {
        return new Student(){
            private Long id = 1L;
            private String cpf = "12345678910";
            private String name = "Student Test";
            private String password = "123456";
            private Integer score = 0;
            private List<Trail> trails = new ArrayList<>();

            public Long getId() {
                return id;
            }

            public String getCpf() {
                return cpf;
            }

            public String getName() {
                return name;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public Integer getScore() {
                return score;
            }

            public void addScore(Integer score) {
                this.score += score;
            }

            public List<Trail> getTrails() {
                return trails;
            }

            public void addTrail(Trail trail) {
                this.trails.add(trail);
            }

            public void removeTrail(Trail trail) {
                this.trails.remove(trail);
            }
        };
    }

    public static StudentDTO mockStudentDTO() {
        return new StudentDTO(mockStudent());
    }

    public static Student mockStudent2() {
        return new Student(){
            private Long id = 2L;
            private String cpf = "23456789101";
            private String name = "Student Test 2";
            private String password = "1234567";
            private Integer score = 0;
            private List<Trail> trails = new ArrayList<>();

            public Long getId() {
                return id;
            }

            public String getCpf() {
                return cpf;
            }

            public String getName() {
                return name;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public Integer getScore() {
                return score;
            }

            public void addScore(Integer score) {
                this.score += score;
            }

            public List<Trail> getTrails() {
                return trails;
            }

            public void addTrail(Trail trail) {
                this.trails.add(trail);
            }

            public void removeTrail(Trail trail) {
                this.trails.remove(trail);
            }
        };
    }

    public static StudentDTO mockStudentDTO2() {
        return new StudentDTO(mockStudent2());
    }

    public static Teacher mockTeacher() {
        return new Teacher(){
            private Long id = 1L;
            private String cpf = "12345678910";
            private String name = "Teacher Test";
            private String password = "123456";
            private List<Trail> trails = new ArrayList<>();

            public Long getId() {
                return id;
            }

            public String getCpf() {
                return cpf;
            }

            public String getName() {
                return name;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public List<Trail> getTrails() {
                return trails;
            }

            public void addTrail(Trail trail) {
                this.trails.add(trail);
            }

            public void removeTrail(Trail trail) {
                this.trails.remove(trail);
            }
        };
    }

    public static TeacherDTO mockTeacherDTO() {
        return new TeacherDTO(mockTeacher());
    }

    public static Trail mockTrail() {
        return new Trail(){
            private Long id = 1L;
            private String name = "Trail Test";
            private String description = "Trail Test Description";
            private List<TrailContent> contents = new ArrayList<>();
            private Teacher teacher = mockTeacher();
            private List<Student> students = new ArrayList<>();

            public Long getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }

            public List<TrailContent> getContents() {
                return contents;
            }

            public void addContent(TrailContent content) {
                contents.add(content);
            }

            public void removeContent(TrailContent content) {
                contents.remove(content);
            }

            public Teacher getTeacher() {
                return teacher;
            }

            public List<Student> getStudents() {
                return students;
            }

            public void addStudent(Student student) {
                this.students.add(student);
            }

            public void removeStudent(Student student) {
                this.students.remove(student);
            }
        };
    }

    public static TrailDTO mockTrailDTO() {
        return new TrailDTO(mockTrail());
    }

    public static TrailContent mockTrailContent() {
        return new TrailContent() {
            private Long id = 1L;
            private String name = "Trail Content Test";
            private String content = "Trail Content Test Content";
            private Integer score = 10;
            private Trail trail = mockTrail();

            public Long getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getContent() {
                return content;
            }

            public Integer getScore() {
                return score;
            }

            public Trail getTrail() {
                return trail;
            }
        };
    }

    public static TrailContentDTO mockTrailContentDTO() {
        return new TrailContentDTO(mockTrailContent());
    }
}
