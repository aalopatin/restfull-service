package ru.watchlist.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.watchlist.domain.GroupParameter;
import ru.watchlist.dto.groupparameter.GroupParameterDTO;
import ru.watchlist.dto.groupparameter.GroupParameterIdDTO;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.TypeReportService;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", uses = { TypeReportService.class})
public interface GroupParameterMapper {

    //GroupParatemerDTO
    GroupParameterDTO toDTO(GroupParameter groupParameter);
    List<GroupParameterDTO> toDTOList(List<GroupParameter> groupParameterList);

    //GroupParameterIdDTO
    @Mapping(target = "typeReportId", source = "typeReport.id")
    GroupParameterIdDTO toIdDTO(GroupParameter groupParameter);
    List<GroupParameterIdDTO> toIdDTOList(List<GroupParameter> groupParameterList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "typeReport", source ="typeReportId", qualifiedByName = { "findTypeReportById" })
    GroupParameter fromIdDTO(GroupParameterIdDTO groupParameterIdDTO) throws EntityNotFoundException;

    //GroupParameter
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "common", ignore = true)
    void fill(GroupParameterIdDTO source, @MappingTarget GroupParameter target);

}
