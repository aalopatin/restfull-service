package ru.watchlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.watchlist.domain.RowSettingReport;
import ru.watchlist.dto.settingreport.RowSettingReportIdDTO;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.ParameterService;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ParameterService.class})
public interface RowSettingReportMapper {

    //RowSettingReportIdDTO
    @Mapping(target = "parameterId", source = "parameter.id")
    RowSettingReportIdDTO toIdDTO(RowSettingReport rowSettingReport);

    @Mapping(target = "parameter", source = "parameterId", qualifiedByName = { "findParameterById" })
    RowSettingReport fromIdDTO(RowSettingReportIdDTO rowSettingReportIdDTO) throws EntityNotFoundException;

}
