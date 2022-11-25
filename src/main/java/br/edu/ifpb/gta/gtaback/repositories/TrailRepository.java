package br.edu.ifpb.gta.gtaback.repositories;

import br.edu.ifpb.gta.gtaback.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrailRepository extends JpaRepository<Trail, Long> {
    public Trail findByName(String name);
}
