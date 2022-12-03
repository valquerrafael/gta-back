package br.edu.ifpb.gta.gtaback.exceptions;

public class UserIsNotRoleException extends RuntimeException {
    public UserIsNotRoleException(String message) {
        super(message);
    }
}
