package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.watchlist.domain.Report;
import ru.watchlist.dto.ReportIdDTO;
import ru.watchlist.mapper.ReportMapper;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reports")
public class ReportController {

    @Autowired
    ReportMapper reportMapper;

    @PostMapping
    public ResponseEntity<List<ReportIdDTO>> createParameter(@RequestBody List<ReportIdDTO> reportIdDTOList) {
        List<Report> reportList = reportMapper.fromReportIdDTOList(reportIdDTOList);

        return new ResponseEntity<>(reportIdDTOList, HttpStatus.OK);
    }

}
