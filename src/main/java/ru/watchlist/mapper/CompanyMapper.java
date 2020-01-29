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
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void fill(Company source, @MappingTarget Company target);

}
