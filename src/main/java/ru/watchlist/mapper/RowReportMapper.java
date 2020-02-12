package ru.watchlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.watchlist.domain.RowReport;
import ru.watchlist.dto.report.RowReportIdDTO;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.ParameterService;

@Mapper(componentModel = "spring", uses = {ParameterService.class})
public interface RowReportMapper {

    //RowSettingReportIdDTO
    @Mapping(target = "parameterId", source = "parameter.id")
    RowReportIdDTO toIdDTO(RowReport rowSettingReport);

    @Mapping(target = "parameter", source = "parameterId", qualifiedByName = { "findParameterById" })
    RowReport fromIdDTO(RowReportIdDTO rowSettingReportIdDTO) throws EntityNotFoundException;


}
