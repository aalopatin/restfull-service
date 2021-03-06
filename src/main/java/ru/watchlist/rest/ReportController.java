package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.Report;
import ru.watchlist.dto.ArrayDTO;
import ru.watchlist.dto.PageReportDTO;
import ru.watchlist.dto.Variant;
import ru.watchlist.dto.report.ReportAbstract;
import ru.watchlist.dto.report.ReportIdDTO;
import ru.watchlist.mapper.ReportMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    ReportService reportService;

    @PostMapping
    public ResponseEntity<List<? extends ReportAbstract>> create(@RequestBody ArrayDTO<ReportIdDTO> arrayDTO) throws EntityNotFoundException {

        List<Report> reportList = reportMapper.fromIdDTOList(arrayDTO.getData());
        reportService.createAll(reportList);
        return new ResponseEntity<>(reportMapper.toIdDTOList(reportList), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<? extends ReportAbstract>> findAll(@RequestParam(value = "variant", required = false) Variant variant,
                                                                  @RequestParam(value = "rows", required = false) boolean rows,
                                                                  @RequestParam(value = "search", required = false) String search) {

        List<Report> reportList = reportService.findAll(search);
        return new ResponseEntity<>(variantDTOList(reportList, variant, rows), HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size", "sortBy"})
    public ResponseEntity<PageReportDTO> findAll(@RequestParam(value = "variant", required = false) Variant variant,
                                                 @RequestParam(value = "rows", required = false) boolean rows,
                                                 @RequestParam(value = "search", required = false) String search,
                                                 @RequestParam(value = "page") Integer page,
                                                 @RequestParam(value = "size") Integer size,
                                                 @RequestParam(value = "sortBy") String sortBy,
                                                 @RequestParam(value = "direction", required = false) Sort.Direction direction) {

        Page<Report> pageReport = reportService.findAll(search, page, size, sortBy, direction);

        PageReportDTO pageReportDTO = new PageReportDTO();
        pageReportDTO.setReports(variantDTOList(pageReport.getContent(), variant, rows));
        pageReportDTO.setTotal(pageReport.getTotalElements());
        pageReportDTO.setPages(pageReport.getTotalPages());

        return new ResponseEntity<>(pageReportDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends ReportAbstract> get(@PathVariable Long id,
                                                               @RequestParam(value = "variant", required = false) Variant variant,
                                                               @RequestParam(value = "rows", required = false) boolean rows) throws EntityNotFoundException {

        Report report = reportService.findById(id);
        return new ResponseEntity<>(variantDTO(variant, rows, report), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) throws EntityNotFoundException {
        reportService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ReportAbstract variantDTO(Variant variant, boolean rows, Report report) throws EntityNotFoundException {
        if (variant == null) variant = Variant.DEFAULT;
        switch (variant) {
            case ID:
                if (rows) {
                    return reportMapper.toIdDTO(report);
                } else {
                    return reportMapper.toIdNoRowsDTO(report);
                }
            default:
                return reportMapper.toIdDTO(report);
        }
    }

    private List<? extends ReportAbstract> variantDTOList(List<Report> reportList, Variant variant, boolean rows) {
        if (variant == null) variant = Variant.DEFAULT;
        switch (variant) {
            case ID:
                if (rows) {
                    return reportMapper.toIdDTOList(reportList);
                } else {
                    return reportMapper.toIdNoRowsDTOList(reportList);
                }
            default:
                return reportMapper.toIdNoRowsDTOList(reportList);
        }
    }

}
