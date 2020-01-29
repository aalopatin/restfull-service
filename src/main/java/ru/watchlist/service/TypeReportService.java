package ru.watchlist.service;

import org.mapstruct.Named;
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
    public TypeReport createTypeReport(TypeReport typeReport) {
        return typeReportRepository.save(typeReport);
    }

    @Named("findTypeReportById")
    public TypeReport findById(Long id) throws EntityNotFoundException {
        if(id != null) {
            TypeReport typeReport = typeReportRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(TypeReport.class, "id", id.toString()));
            return typeReport;
        } else {
            return null;
        }
    }

    public List<TypeReport> getAll() {
        return typeReportRepository.findAll();
    }

    public TypeReport saveTypeReport(Long id, TypeReport typeReport) throws EntityNotFoundException {
        TypeReport typeReportFromDB = findById(id);
        typeReportMapper.fill(typeReport, typeReportFromDB);
        return typeReportRepository.save(typeReportFromDB);
    }

    public void deleteTypeReport(Long id) throws EntityNotFoundException {
        try {
            typeReportRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException(TypeReport.class, "id", id.toString());
        }
    }

}
