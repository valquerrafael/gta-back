package br.edu.ifpb.gta.gtaback.repository;

import br.edu.ifpb.gta.gtaback.model.TrailContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrailContentRepository extends JpaRepository<TrailContent, Long> {
    TrailContent findByName(String name);
}
