package br.edu.ifpb.gta.gtaback.repository;

import br.edu.ifpb.gta.gtaback.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByCpf(String cpf);
}
