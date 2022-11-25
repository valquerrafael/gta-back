package br.edu.ifpb.gta.gtaback.repositories;

import br.edu.ifpb.gta.gtaback.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    public Institution findByName(String name);
}
