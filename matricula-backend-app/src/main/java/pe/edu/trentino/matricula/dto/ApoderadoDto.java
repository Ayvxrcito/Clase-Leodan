package pe.edu.trentino.matricula.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApoderadoDto {
    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
}
