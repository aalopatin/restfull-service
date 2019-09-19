package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.company.Company;
import ru.watchlist.dto.company.CompanyDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    //CompanyDTO
    CompanyDTO toCompanyDTO(Company company);
    List<CompanyDTO> toCompanyDTOList(List<Company> companies);
    Company fromCompanyDTO(CompanyDTO companyDTO);

    //Company
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void fillCompany(Company source, @MappingTarget Company target);

}
