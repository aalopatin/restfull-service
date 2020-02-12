package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.Company;
import ru.watchlist.dto.company.CompanyDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    //CompanyDTO
    CompanyDTO toDTO(Company company);
    List<CompanyDTO> toDTOList(List<Company> companies);
    Company fromDTO(CompanyDTO companyDTO);

    //Company
    @Mapping(target = "id", ignore = true)
    void fill(CompanyDTO source, @MappingTarget Company target);

}
