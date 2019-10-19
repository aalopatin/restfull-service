package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.report.TypeReport;
import ru.watchlist.dto.report.TypeReportDTO;
import ru.watchlist.mapper.TypeReportMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.TypeReportService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/typereports")
public class TypeReportController {

    @Autowired
    private TypeReportService typeReportService;

    @Autowired
    private TypeReportMapper typeReportMapper;

    @PostMapping
    public ResponseEntity<TypeReportDTO> createTypeReport(@RequestBody TypeReportDTO TypeReportDTO) {
        TypeReport TypeReport = typeReportMapper.fromTypeReportDTO(TypeReportDTO);
        TypeReport = typeReportService.createTypeReport(TypeReport);
        return new ResponseEntity<>(typeReportMapper.toTypeReportDTO(TypeReport), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeReportDTO> getTypeReport(@PathVariable Long id) throws EntityNotFoundException {
        TypeReport TypeReport = typeReportService.findById(id);
        return new ResponseEntity<>(typeReportMapper.toTypeReportDTO(TypeReport), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TypeReportDTO>> getAllTypeReport() {
        List<TypeReport> companies = typeReportService.getAll();
        return new ResponseEntity<>(typeReportMapper.toTypeReportDTOList(companies), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeReportDTO> saveTypeReport(@PathVariable Long id, @RequestBody TypeReportDTO TypeReportDTO) throws EntityNotFoundException {
        TypeReport TypeReport = typeReportMapper.fromTypeReportDTO(TypeReportDTO);
        TypeReport = typeReportService.saveTypeReport(id, TypeReport);
        return new ResponseEntity<>(typeReportMapper.toTypeReportDTO(TypeReport), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTypeReport(@PathVariable Long id) throws EntityNotFoundException {
        typeReportService.deleteTypeReport(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
