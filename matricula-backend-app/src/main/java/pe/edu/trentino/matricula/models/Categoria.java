package pe.edu.trentino.matricula.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //es una anotacion que combina getter setter y tostring
@AllArgsConstructor //generar un constructor con argumentos (con parametros)
@NoArgsConstructor // generar un constructor sin argumentos (sin parametros)
@Entity //Marca la clase como una entidad lo cual sera mapeada a una tabla en la base de datos
@Table(name = "categorias") /*Se utilza para especificar los detalles de una tabla a lña que se mapeara
un entidad, puedes usarala para personalizar el nombre de la tbla y otros campos con la base de datos,
como indices, esquemas y restricciones unicas
*/
public class Categoria {
    @Id // indicar el campo id (la clave primaria)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera el valor automaticamente
    private Long id;

    private String nombre;
    private String descripcion;
}
