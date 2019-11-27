package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.report.Parameter;
import ru.watchlist.dto.report.ParameterDTO;
import ru.watchlist.dto.report.ParameterIdDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParameterMapper {

    //ParameterDTO
    ParameterDTO toParameterDTO(Parameter parameter);
    List<ParameterDTO> toParameterDTOList(List<Parameter> parameters);
    Parameter fromParameterDTO(ParameterDTO parameterDTO);

    //ParameterIdDTO
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "groupTitle", source = "group.title")
    ParameterIdDTO toParameterIdDTO(Parameter parameter);

    List<ParameterIdDTO> toParameterIdDTOList(List<Parameter> parameters);

    @Mapping(target = "group.id", source = "groupId")
    Parameter fromParameterIdDTO(ParameterIdDTO parameterIdDTO);

    //Parameter
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "id", ignore = true)
    void fillParameter(Parameter source, @MappingTarget Parameter target);
    
}
