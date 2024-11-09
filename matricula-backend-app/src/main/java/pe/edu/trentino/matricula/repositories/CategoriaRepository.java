package pe.edu.trentino.matricula.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.trentino.matricula.models.Categoria;

/*es un componente o interfaz, actua como un intermediario entre la logica de negocio (servicios)
* y la capa de persitencia (base de datos), gestionar la interaccion con la base de datos, permite
* realizar operaciones sql (CRUD), sin la necesidad de escribir codigo SQL*/
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
