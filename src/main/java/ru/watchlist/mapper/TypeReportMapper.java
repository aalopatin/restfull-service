package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.TypeReport;
import ru.watchlist.dto.TypeReportDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeReportMapper {

    //TypeReportDTO
    TypeReportDTO toTypeReportDTO(TypeReport TypeReport);
    List<TypeReportDTO> toTypeReportDTOList(List<TypeReport> companies);
    TypeReport fromTypeReportDTO(TypeReportDTO TypeReportDTO);

    //TypeReport
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void fillTypeReport(TypeReport source, @MappingTarget TypeReport target);

}
