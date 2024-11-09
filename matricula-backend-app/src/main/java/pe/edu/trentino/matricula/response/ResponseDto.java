package pe.edu.trentino.matricula.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {
    private int status;
    private String message;

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
