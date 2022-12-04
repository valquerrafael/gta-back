package br.edu.ifpb.gta.gtaback.DTOs;

import br.edu.ifpb.gta.gtaback.models.User;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.gta.gtaback.models.Role;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private Long institutionId;
    private List<Long> trailsIds = new ArrayList<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.institutionId = user.getInstitution().getId();
        if (user.getTrails() != null)
            user.getTrails().forEach(trail -> trailsIds.add(trail.getId()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Long getInstitutionId() {
        return institutionId;
    }

    public List<Long> getTrailsIds() {
        return trailsIds;
    }
}
