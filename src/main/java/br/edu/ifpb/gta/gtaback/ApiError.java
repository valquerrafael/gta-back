package br.edu.ifpb.gta.gtaback;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ApiError extends Throwable {
    private final HttpStatus status;
    private final String message;
    private List<String> errors = new ArrayList<>();

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors.add(error);
    }
}
