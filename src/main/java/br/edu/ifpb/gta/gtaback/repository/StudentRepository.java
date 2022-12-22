package br.edu.ifpb.gta.gtaback.repository;

import br.edu.ifpb.gta.gtaback.model.Student;
import br.edu.ifpb.gta.gtaback.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByCpf(String cpf);

    @Query("SELECT t FROM Student s JOIN s.trails t WHERE s.id = ?1 ORDER BY t.name ASC")
    List<Trail> findTrails(Long id);

    @Query("SELECT s FROM Student s ORDER BY s.score DESC")
    List<Student> findRanking();
}
