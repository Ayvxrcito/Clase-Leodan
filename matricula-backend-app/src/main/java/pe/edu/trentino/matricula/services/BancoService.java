package pe.edu.trentino.matricula.services;

import jakarta.servlet.http.HttpServletResponse;
import pe.edu.trentino.matricula.dto.BancoDto;
import pe.edu.trentino.matricula.models.Banco;
import pe.edu.trentino.matricula.response.PaginatedResponseDto;
import pe.edu.trentino.matricula.response.ResponseDto;

import java.io.IOException;
import java.util.List;

public interface BancoService {
    ResponseDto crearBanco(BancoDto bancoDto);
    PaginatedResponseDto<Banco> obtenerBancos(String nombre, int page, int perPage);
    ResponseDto actualizarBanco(Long id, BancoDto bancoDto);
    ResponseDto eliminarBanco(Long id);
    void descargarBanco(HttpServletResponse response) throws IOException;
}
