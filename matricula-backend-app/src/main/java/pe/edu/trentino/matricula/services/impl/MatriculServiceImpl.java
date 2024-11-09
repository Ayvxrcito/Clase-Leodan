package pe.edu.trentino.matricula.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pe.edu.trentino.matricula.Config.HandlerException;
import pe.edu.trentino.matricula.dto.MatriculaDtoRequest;
import pe.edu.trentino.matricula.models.Alumno;
import pe.edu.trentino.matricula.models.Grado;
import pe.edu.trentino.matricula.models.Matricula;
import pe.edu.trentino.matricula.repositories.*;
import pe.edu.trentino.matricula.response.ResponseDto;
import pe.edu.trentino.matricula.services.MatriculaService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MatriculServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlumnoRepository alumnoRepository;
    private final GradoRepository gradoRepository;
    private final SeccionRepository seccionRepository;
    private final NivelRepository nivelRepository;
    private final ApoderadoRepository apoderadoRepository;


    @Override
    public ResponseDto matricularAlumno(MatriculaDtoRequest matriculaDto) {
        var response = new ResponseDto();
        try {
            //Obtener datos del alumno
            Alumno alumno = alumnoRepository.findById(matriculaDto.getAlumnoId())
                    .orElseThrow(() -> new HandlerException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
            Matricula matricula = new Matricula();
            matricula.setCodigo(matricula.getCodigo());
            matricula.setCodigo(matricula.getCodigo());
            matricula.setApoderado(apoderadoRepository.findById(matriculaDto.getApoderadoId())
                    .orElseThrow(()->new HandlerException(HttpStatus.NOT_FOUND, "Apoderado no encontrado")));

            matricula.setSeccion(seccionRepository.findById(matriculaDto.getSeccionId())
                    .orElseThrow(()->new HandlerException(HttpStatus.NOT_FOUND,"Seccion no encontrada")));

            matricula.setNivel(nivelRepository.findById(matriculaDto.getNivelId())
                    .orElseThrow(()->new HandlerException(HttpStatus.NOT_FOUND,"Nivel Educativo no encontrado")));

            matricula.setGrado((Grado) gradoRepository.findById(matriculaDto.getGradoId())
                    .orElseThrow(()->new HandlerException(HttpStatus.NOT_FOUND,"Grado no encontrado")));

            matricula.setFechaMatricula(LocalDateTime.now());
            matricula.setSituacion(matriculaDto.getSituacion());
            matricula.setProcedencia(matriculaDto.getProcedencia());
            matricula.setParentesco(matriculaDto.getParentesco());
            matricula.setInstitucionProcedencia(matriculaDto.getInstitucionProcedencia());
            matricula.setCostoMatricula(matriculaDto.getCostoMatricula());
            matricula.setCostoMensualidad(matriculaDto.getCostoMensualidad());
            matricula.setDescuentoMensualidad(matriculaDto.getDescuendoMensualidad());
            matricula.setMensualidadFinal(matriculaDto.getMensualidadFinal());


            //Guarda la información en base de datos
            matriculaRepository.save(matricula);

            response.setStatus(200);
            response.setMessage("Alumno(a) " + alumno.getNombres()
                    + " " + alumno.getApellidos()
                    + "Matriculado correctamente"
            );

        } catch (Exception e) {
            throw new HandlerException(
                    HttpStatus.NOT_FOUND,
                    "El alumno(a) ya se encuentra matriculado"
            );
        }
        return null;
    }

    private String generateCodigoMatricula(String nombre, String apellidos, String dni, int anio) {

        String iniciaNombre = !nombre.isEmpty() ? nombre.substring(0,1).toUpperCase() : "";
        String inicialApellido = !apellidos.isEmpty() ? apellidos.split(" ")[0].substring(0,1).toUpperCase() : "";
        return  iniciaNombre + inicialApellido + dni + anio;
    }
}
