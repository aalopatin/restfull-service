package ru.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.watchlist.domain.Report;
import ru.watchlist.mapper.ReportMapper;
import ru.watchlist.repository.ReportRepository;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.specification.SpecificationBuilder;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    ReportMapper reportMapper;

    public List<Report> createAll(List<Report> reportList) {
        return reportRepository.saveAll(reportList);
    }

    public List<Report> findAll(String search) {
        SpecificationBuilder<Report> builder = new SpecificationBuilder<>(search);
        Specification<Report> specification = builder.build();
        return reportRepository.findAll(specification);
    }

    public Page<Report> findAll(String search, Integer page, Integer size, String sortBy, Sort.Direction direction) {

        SpecificationBuilder<Report> builder = new SpecificationBuilder<>(search);
        Specification<Report> specification = builder.build();

        Sort sort = new Sort(direction, sortBy);

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<Report> reportPage = reportRepository.findAll(specification, pageRequest);
        return reportPage;

    }

    public Report findById(Long id) throws EntityNotFoundException {

        Report report = reportRepository.findById(id).orElse(null);

        if (report==null) {
            throw new EntityNotFoundException(Report.class, "id", id.toString());
        }

        return report;
    }

    public void delete(Long id) throws EntityNotFoundException {
        try {
            reportRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException(Report.class, "id", id.toString());
        }
    }
}
