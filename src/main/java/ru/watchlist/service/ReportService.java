package ru.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.watchlist.domain.Report;
import ru.watchlist.mapper.ReportMapper;
import ru.watchlist.repository.ReportRepository;

import java.util.List;

public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    ReportMapper reportMapper;

    public Report createReport(Report report) {
        Report createdReport = reportRepository.save(report);
        return createdReport;
    }

//    public List<Report> createReports(List<Report> reports) {
//
//        return
//    }

}
