package br.edu.ifpb.gta.gtaback.repository;

import br.edu.ifpb.gta.gtaback.model.Teacher;
import br.edu.ifpb.gta.gtaback.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByCpf(String cpf);
    @Query("SELECT tr FROM Teacher t JOIN t.trails tr WHERE t.id = ?1 ORDER BY tr.name ASC")
    List<Trail> findTrails(Long id);
}
