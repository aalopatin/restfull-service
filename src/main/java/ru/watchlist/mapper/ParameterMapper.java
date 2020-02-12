package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.Parameter;
import ru.watchlist.dto.parameter.ParameterIdDTO;
import ru.watchlist.dto.parameter.ParameterDTO;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.GroupParameterService;

import java.util.List;

@Mapper(componentModel = "spring", uses = { GroupParameterService.class })
public interface ParameterMapper {

    //ParameterDTO
    ParameterDTO toDTO(Parameter parameter);
    List<ParameterDTO> toDTOList(List<Parameter> parameterList);

    //ParameterIdDTO
    @Mapping(target = "groupId", source = "group.id")
    ParameterIdDTO toIdDTO(Parameter parameter);
    List<ParameterIdDTO> toIdDTOList(List<Parameter> parameters);

    @Mapping(target ="id", ignore = true)
    @Mapping(target = "group", source = "groupId", qualifiedByName = { "findGroupById" })
    Parameter fromIdDTO(ParameterIdDTO parameterIdDTO) throws EntityNotFoundException;

    //Parameter
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cumulative", ignore = true)
    void fill(ParameterIdDTO source, @MappingTarget Parameter target);
    
}
