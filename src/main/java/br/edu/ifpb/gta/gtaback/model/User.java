package br.edu.ifpb.gta.gtaback.model;

import br.edu.ifpb.gta.gtaback.services.Util.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private Role role;
    @OneToMany(
        cascade = CascadeType.PERSIST,
        orphanRemoval = false
    )
    private List<Trail> trails;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public List<Trail> getTrails() {
        return trails;
    }

    public void addTrail(Trail trail) {
        trails.add(trail);
    }

    public void removeTrail(Trail trail) {
        trails.remove(trail);
    }
}
