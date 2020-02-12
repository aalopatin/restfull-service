package ru.watchlist.service;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.watchlist.domain.TypeReport;
import ru.watchlist.dto.typereport.TypeReportDTO;
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
            return typeReportRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(TypeReport.class, "id", id.toString()));
        } else {
            return null;
        }
    }

    public List<TypeReport> getAll() {
        return typeReportRepository.findAll();
    }

    public TypeReport save(Long id, TypeReportDTO typeReportDTO) throws EntityNotFoundException {
        TypeReport typeReportFromDB = findById(id);
        typeReportMapper.fill(typeReportDTO, typeReportFromDB);
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
