package pe.edu.trentino.matricula.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.trentino.matricula.dto.CategoriaDto;
import pe.edu.trentino.matricula.models.Categoria;
import pe.edu.trentino.matricula.repositories.CategoriaRepository;
import pe.edu.trentino.matricula.services.CategoriaService;

import java.util.List;

/* esta clase se encarga de encapsular la logica de negocio de la aplicacion, este servicio actua como
* un intermediario entre los controladores y la capa de persistencia (base de datos). Los servicios proporcionan metodos
* que implementa la logica de negocio y realizan operaciones sobre esos datos.*/

@Service //marcar la clase como un servicio que contiene la logica de negocio
@RequiredArgsConstructor //generar un constructor que incluye los campos final
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository; //inyectar la dependencia del repositorio de categorias

    @Override
    public Categoria crearCategoria(CategoriaDto dto) {
        //es una instancia para crear un nuevo objeto en java, de la clase Categoria
        Categoria nuevaCategoria = new Categoria(); //La palabra clave new se usa para crear una nueva instancia
        nuevaCategoria.setNombre(dto.getNombre()); //Asignar los valores al nuevo objeto
        nuevaCategoria.setDescripcion(dto.getDescripcion());
        return categoriaRepository.save(nuevaCategoria); //Almacena el nuevo objeto en la base de datos
    }

    @Override
    public List<Categoria> obtenerCategorias() {
        //retorna una lista de todas las categorias almacenadas en la base de datos
        // findAll se usa para recupear toda las entidades de la lista
        return categoriaRepository.findAll().stream().toList();
    }
}
