package ru.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.watchlist.domain.SettingReport;
import ru.watchlist.mapper.SettingReportMapper;
import ru.watchlist.repository.SettingReportRepository;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.specification.SpecificationBuilder;

import java.util.List;

@Service
public class SettingReportService {

    @Autowired
    SettingReportRepository settingReportRepository;

    @Autowired
    SettingReportMapper settingReportMapper;

    public SettingReport createSettingReport(SettingReport settingReport) {
        SettingReport createdSettingReport = settingReportRepository.save(settingReport);
        return createdSettingReport;
    }

    public List<SettingReport> findAll() {
        return settingReportRepository.findAll();
    }

    public List<SettingReport> findAll(String search) {

        SpecificationBuilder<SettingReport> builder = new SpecificationBuilder<>(search);
        Specification<SettingReport> specification = builder.build();
        return settingReportRepository.findAll(specification);

    }

    public SettingReport findById(Long id) throws EntityNotFoundException {

        SettingReport settingReport = settingReportRepository.findById(id).orElse(null);

        if (settingReport==null) {
            throw new EntityNotFoundException(SettingReport.class, "id", id.toString());
        }

        return settingReport;
    }

    public SettingReport saveSettingReport(Long id, SettingReport settingReport) throws EntityNotFoundException {
        SettingReport settingReportFromDB = findById(id);
        settingReportMapper.fillSettingReport(settingReport, settingReportFromDB);
        settingReportRepository.save(settingReportFromDB);
        return settingReportFromDB;
    }
}
