package br.edu.ifpb.gta.gtaback.models;

public enum Role {
    STUDENT("student"),
    TEACHER("teacher");

    private final String role;
    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
