package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.report.Period;
import ru.watchlist.dto.report.PeriodDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeriodMapper {

    //PeriodDTO
    PeriodDTO toPeriodDTO(Period period);
    List<PeriodDTO> toPeriodDTOList(List<Period> periods);
    Period fromPeriodDTO(PeriodDTO periodDTO);

    //Period
    @Mapping(target = "id", ignore = true)
    void fillPeriod(Period source, @MappingTarget Period target);
}
