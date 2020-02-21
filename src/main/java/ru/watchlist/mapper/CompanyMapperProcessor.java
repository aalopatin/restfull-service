package ru.watchlist.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.watchlist.domain.Company;
import ru.watchlist.dto.company.CompanyDTO;
import ru.watchlist.service.FileStorageService;

@Mapper(componentModel = "spring")
public abstract class CompanyMapperProcessor {

    @Autowired
    private FileStorageService fileStorageService;

    @AfterMapping
    public void setLogoURI(Company company, @MappingTarget CompanyDTO dto) {
        dto.setLogoURI( fileStorageService.getLogoURI(company.getLogo()) );
    }

}
