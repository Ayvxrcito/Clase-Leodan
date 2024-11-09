package pe.edu.trentino.matricula.Config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class HandlerException extends RuntimeException{
    private final HttpStatus status;
    private final String message;
}
