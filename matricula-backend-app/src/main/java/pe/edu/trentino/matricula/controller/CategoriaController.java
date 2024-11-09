package pe.edu.trentino.matricula.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.trentino.matricula.dto.CategoriaDto;
import pe.edu.trentino.matricula.services.CategoriaService;

/*
Es una clase que maneja solicitudes HTTP (GET, POST, PUT, DELETE, etc) entrantes,
normalmente se aplican en applicaciones web o APIs REST.
los contoladors actuan de intermediarios entre la capa de presentacion y la capa de servicio
* */
@RestController //marca la clase como un controlador
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping("crear-categoria") //anotacion para manejar solicutd de tipo POST
    //@RequestBody Vincula el cuerpo de la solicitud (JSON o XML) a un objeto java
    public ResponseEntity<?> crearCategoria(@RequestBody CategoriaDto dto) {
        return ResponseEntity.ok(categoriaService.crearCategoria(dto));
    }

    @GetMapping("obtener-categorias") //anotacion para manejar solicutd de tipo GET
    public ResponseEntity<?> obtenerCategorias() {
        return ResponseEntity.ok(categoriaService.obtenerCategorias());
    }
}
