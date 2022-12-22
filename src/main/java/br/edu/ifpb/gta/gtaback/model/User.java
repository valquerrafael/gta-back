package br.edu.ifpb.gta.gtaback.model;

import java.util.List;

public interface User {
    Long getId();
    String getCpf();
    String getName();
    String getPassword();
    void setPassword(String password);
    List<Trail> getTrails();
    void addTrail(Trail trail);
    void removeTrail(Trail trail);
}
