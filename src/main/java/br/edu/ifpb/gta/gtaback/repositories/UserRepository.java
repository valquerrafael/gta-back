package br.edu.ifpb.gta.gtaback.repositories;

import br.edu.ifpb.gta.gtaback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
