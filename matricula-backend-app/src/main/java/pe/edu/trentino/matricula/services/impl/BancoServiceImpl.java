package pe.edu.trentino.matricula.services.impl;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.edu.trentino.matricula.dto.BancoDto;
import pe.edu.trentino.matricula.models.Banco;
import pe.edu.trentino.matricula.repositories.BancoRepository;
import pe.edu.trentino.matricula.response.PaginatedResponseDto;
import pe.edu.trentino.matricula.response.ResponseDto;
import pe.edu.trentino.matricula.services.BancoService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

@RequiredArgsConstructor
@Service
public class BancoServiceImpl implements BancoService {

    // Inyeccion de dependencias
    private final BancoRepository bancoRepository;

    @Override
    public ResponseDto crearBanco(BancoDto bancoDto) {
        var response = new ResponseDto();
        Optional<Banco> existeNombre = bancoRepository.findByNombre(bancoDto.getNombre());
        if (existeNombre.isPresent()) {
            response.setStatus(422);
            response.setMessage(String.format("Ya existe un banco con el nombre %s", bancoDto.getNombre()));
            return response;
        }

        try {
            Banco nuevoBanco = new Banco();
            nuevoBanco.setNombre(bancoDto.getNombre());
            nuevoBanco.setDireccion(bancoDto.getDireccion());
            nuevoBanco.setCodigo(bancoDto.getCodigo());

            bancoRepository.save(nuevoBanco);

            response.setStatus(200);
            response.setMessage("Banco creado correctamente");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public PaginatedResponseDto<Banco> obtenerBancos(String nombre, int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Banco> bancosPage = bancoRepository
                .findByNombreContainingIgnoreCase(nombre, pageable);

        return new PaginatedResponseDto<>(
                bancosPage.getContent(),
                page,
                perPage,
                bancosPage.getTotalElements()
        );
    }

    @Override
    public ResponseDto actualizarBanco(Long id, BancoDto bancoDto) {
        var response = new ResponseDto();

        try {
            Optional<Banco> optionalBanco = bancoRepository.findById(id);
            if (optionalBanco.isPresent()) {
                var banco = optionalBanco.get();
                banco.setNombre(bancoDto.getNombre());
                banco.setDireccion(bancoDto.getDireccion());
                banco.setCodigo(bancoDto.getCodigo());
                bancoRepository.save(banco);

                response.setStatus(200);
                response.setMessage("Banco actualizado correctamente");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public ResponseDto eliminarBanco(Long id) {
        var response = new ResponseDto();
        try {
            Optional<Banco> optionalBanco = bancoRepository.findById(id);
            if (optionalBanco.isPresent()) {
                bancoRepository.deleteById(id);
                response.setStatus(200);
                response.setMessage("Banco eliminado correctamente");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public void descargarBanco(HttpServletResponse response) throws IOException {
        /* Creamos el documento y la primera hoja(Clientes) */
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Bancos");

        /* Configuramos ancho columna 1, las otras ya quedan bien por defecto */
        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 5000);

        /* Configuramos  los estilos */
        HSSFFont bold = workbook.createFont();
        HSSFCellStyle styleBold = workbook.createCellStyle();
        styleBold.setFont( bold );

        /* Definimos los encabezados*/
        HSSFRow headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Nombre", "Dirección", "Código"};
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(styleBold);
            cell.setCellValue(headers[i]);
        }

        /* Obtener los datos de los bancos */
        List<Banco> bancos = bancoRepository.findAll();

        /* Guardamos los datos en el documento */
        int rownum = 1; //fila 0 para los encabezados
        for (Banco banco : bancos) {
            HSSFRow row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(banco.getId());
            row.createCell(1).setCellValue(banco.getNombre());
            row.createCell(2).setCellValue(banco.getDireccion());
            row.createCell(3).setCellValue(banco.getCodigo());
        }

        /* Guardamos el archivo, en este caso lo devolvemos por un servlet */
        String filename = "bancos-" + System.currentTimeMillis();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename + ".xls", StandardCharsets.UTF_8));
        ServletOutputStream os = response.getOutputStream();
        workbook.write(os);
        os.flush();
        os.close();
    }

}
