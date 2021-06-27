package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.Company;
import ru.watchlist.dto.company.CompanyDTO;
import ru.watchlist.service.FileStorageService;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompanyMapperProcessor.class})
public interface CompanyMapper {

    //CompanyDTO
    CompanyDTO toDTO(Company company);
    List<CompanyDTO> toDTOList(List<Company> companies);
    Company fromDTO(CompanyDTO companyDTO);

    //Company
    @Mapping(target = "id", ignore = true)
    void fill(CompanyDTO source, @MappingTarget Company target);

}
