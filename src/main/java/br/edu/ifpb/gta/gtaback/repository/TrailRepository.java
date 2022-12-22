package br.edu.ifpb.gta.gtaback.repository;

import br.edu.ifpb.gta.gtaback.model.Student;
import br.edu.ifpb.gta.gtaback.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrailRepository extends JpaRepository<Trail, Long> {
    Trail findByName(String name);
    @Query("SELECT s FROM Trail t JOIN t.students s WHERE t.id = ?1 ORDER BY s.name ASC")
    List<Student> findStudents(Long id);
}
