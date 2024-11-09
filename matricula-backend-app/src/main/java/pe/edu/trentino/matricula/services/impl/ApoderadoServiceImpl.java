package pe.edu.trentino.matricula.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.edu.trentino.matricula.dto.ApoderadoDto;
import pe.edu.trentino.matricula.dto.BancoDto;
import pe.edu.trentino.matricula.models.Apoderado;
import pe.edu.trentino.matricula.models.Banco;
import pe.edu.trentino.matricula.repositories.ApoderadoRepository;
import pe.edu.trentino.matricula.response.PaginatedResponseDto;
import pe.edu.trentino.matricula.response.ResponseDto;
import pe.edu.trentino.matricula.services.ApoderadoService;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ApoderadoServiceImpl implements ApoderadoService {
    private final ApoderadoRepository apoderadoRepository;

    @Override
    public ResponseDto crearApoderado(ApoderadoDto apoderadoDto) {
        var response = new ResponseDto();
        Optional<Apoderado> existeDni = apoderadoRepository.findByDni(apoderadoDto.getDni());
        if (existeDni.isPresent()) {
            response.setStatus(422);
            response.setMessage(String.format("Ya existe un Apoderado con el dni %s", apoderadoDto.getDni()));
        return response;
    }
        try {
            Apoderado nuevoApoderado = new Apoderado();
            nuevoApoderado.setDni(apoderadoDto.getDni());
            nuevoApoderado.setNombres(apoderadoDto.getNombres());
            nuevoApoderado.setApellidos(apoderadoDto.getApellidos());
            nuevoApoderado.setEmail(apoderadoDto.getEmail());
            nuevoApoderado.setTelefono(apoderadoDto.getTelefono());

            apoderadoRepository.save(nuevoApoderado);

            response.setStatus(200);
            response.setMessage("Apoderado creado correctamente");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public PaginatedResponseDto<Apoderado> obtenerApoderado(String nombre, int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Apoderado> apoderadoPage = apoderadoRepository
                .findByNombresContainingIgnoreCase(nombre, pageable);
        return new PaginatedResponseDto<>(
                apoderadoPage.getContent(),
                page,
                perPage,
                apoderadoPage.getTotalElements()
        );
    }

    @Override
    public ResponseDto actualizarApoderado(Long id, ApoderadoDto apoderadoDto) {
        var response = new ResponseDto();

        try {
            Optional<Apoderado> optionalApoderado= apoderadoRepository.findById(id);
            if (optionalApoderado.isPresent()) {
                var apoderado = optionalApoderado.get();
                apoderado.setDni(apoderadoDto.getDni());
                apoderado.setNombres(apoderadoDto.getNombres());
               apoderado.setApellidos(apoderadoDto.getApellidos());
                apoderado.setEmail(apoderadoDto.getEmail());
                apoderado.setTelefono(apoderadoDto.getTelefono());
                apoderadoRepository.save(apoderado);

                response.setStatus(200);
                response.setMessage("Apoderado actualizado correctamente");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ResponseDto eliminarApoderado(Long id) {
        var response = new ResponseDto();
        try {
            Optional<Apoderado> optionalApoderado = apoderadoRepository.findById(id);
            if (optionalApoderado.isPresent()) {
                apoderadoRepository.deleteById(id);
                response.setStatus(200);
                response.setMessage("Apoderado eliminado correctamente");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
