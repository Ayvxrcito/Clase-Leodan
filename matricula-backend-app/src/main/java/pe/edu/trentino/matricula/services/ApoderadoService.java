package pe.edu.trentino.matricula.services;

import jakarta.servlet.http.HttpServletResponse;
import pe.edu.trentino.matricula.dto.ApoderadoDto;

import pe.edu.trentino.matricula.models.Apoderado;

import pe.edu.trentino.matricula.response.PaginatedResponseDto;
import pe.edu.trentino.matricula.response.ResponseDto;

import java.io.IOException;

public interface ApoderadoService {
    ResponseDto crearApoderado(ApoderadoDto apoderadoDto);
    PaginatedResponseDto<Apoderado> obtenerApoderado(String nombre, int page, int perPage);
    ResponseDto actualizarApoderado(Long id, ApoderadoDto apoderadoDto);
    ResponseDto eliminarApoderado(Long id);

}
