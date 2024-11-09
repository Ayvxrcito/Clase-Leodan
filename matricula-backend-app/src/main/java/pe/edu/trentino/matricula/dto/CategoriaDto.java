package pe.edu.trentino.matricula.dto;

/* DTO (Data Transfer Object) es un objeto para transferir datos entre diferentes capas de una aplkicacion
* entre la capa de servicio y la base de datos,*/

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaDto {
    private String nombre;
    private String descripcion;
}
