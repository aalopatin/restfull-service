package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.TypeReport;
import ru.watchlist.dto.typereport.TypeReportDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeReportMapper {

    //TypeReportDTO
    TypeReportDTO toDTO(TypeReport TypeReport);
    List<TypeReportDTO> toDTOList(List<TypeReport> companies);
    @Mapping(target = "id", ignore = true)
    TypeReport fromDTO(TypeReportDTO TypeReportDTO);

    //TypeReport
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fill(TypeReport source, @MappingTarget TypeReport target);

}
