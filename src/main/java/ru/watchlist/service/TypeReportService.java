package ru.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.watchlist.domain.TypeReport;
import ru.watchlist.mapper.TypeReportMapper;
import ru.watchlist.repository.TypeReportRepository;
import ru.watchlist.rest.exception.EntityNotFoundException;

import java.util.List;

@Service
public class TypeReportService {

    @Autowired
    TypeReportRepository typeReportRepository;

    @Autowired
    TypeReportMapper typeReportMapper;

    //Repository
    public TypeReport findById(Long id) throws EntityNotFoundException {

        TypeReport typeReport = typeReportRepository.findById(id).orElse(null);

        if (typeReport==null) {
            throw new EntityNotFoundException(TypeReport.class, "id", id.toString());
        }

        return typeReport;
    }

    public List<TypeReport> getAll() {
        return typeReportRepository.findAll();
    }

    public TypeReport createTypeReport(TypeReport typeReport) {
        TypeReport createdTypeReport = typeReportRepository.save(typeReport);
        return createdTypeReport;
    }

    public TypeReport saveTypeReport(Long id, TypeReport typeReport) throws EntityNotFoundException {
        TypeReport typeReportFromDB = findById(id);
        typeReportMapper.fillTypeReport(typeReport, typeReportFromDB);
        typeReportRepository.save(typeReportFromDB);
        return typeReportFromDB;
    }

    public void deleteTypeReport(Long id) throws EntityNotFoundException {
        try {
            typeReportRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException(TypeReport.class, "id", id.toString());
        }

    }

}
