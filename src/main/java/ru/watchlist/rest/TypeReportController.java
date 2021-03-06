package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.TypeReport;
import ru.watchlist.dto.typereport.TypeReportDTO;
import ru.watchlist.mapper.TypeReportMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.TypeReportService;

import java.util.List;

@RestController
@RequestMapping("/api/typesreports")
public class TypeReportController {

    @Autowired
    private TypeReportService typeReportService;

    @Autowired
    private TypeReportMapper typeReportMapper;

    @PostMapping
    public ResponseEntity<TypeReportDTO> createTypeReport(@RequestBody TypeReportDTO typeReportDTO) {
        TypeReport typeReport = typeReportMapper.fromDTO(typeReportDTO);
        typeReportService.createTypeReport(typeReport);
        return new ResponseEntity<>(typeReportMapper.toDTO(typeReport), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeReportDTO> getTypeReport(@PathVariable Long id) throws EntityNotFoundException {
        TypeReport typeReport = typeReportService.findById(id);
        return new ResponseEntity<>(typeReportMapper.toDTO(typeReport), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TypeReportDTO>> findAllTypeReport() {
        List<TypeReport> typesReports = typeReportService.getAll();
        return new ResponseEntity<>(typeReportMapper.toDTOList(typesReports), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeReportDTO> save(@PathVariable Long id, @RequestBody TypeReportDTO typeReportDTO) throws EntityNotFoundException {
        TypeReport typeReport = typeReportService.save(id, typeReportDTO);
        return new ResponseEntity<>(typeReportMapper.toDTO(typeReport), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTypeReport(@PathVariable Long id) throws EntityNotFoundException {
        typeReportService.deleteTypeReport(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
