package pe.edu.trentino.matricula.services;

import pe.edu.trentino.matricula.dto.CategoriaDto;
import pe.edu.trentino.matricula.models.Categoria;

import java.util.List;

//La interfaz define los metodos que el servicio debe proporcionar, mientras que la clase que implementa
// esta interfaz se encarga de realizae las operaciones especificas
public interface CategoriaService {
    Categoria crearCategoria(CategoriaDto dto);
    List<Categoria> obtenerCategorias();
}
