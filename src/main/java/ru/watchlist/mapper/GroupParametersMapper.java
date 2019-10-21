package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.report.GroupParameters;
import ru.watchlist.dto.report.GroupParametersDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupParametersMapper {

    //GroupParametersDTO
    GroupParametersDTO toGroupParametersDTO(GroupParameters GroupParameters);
    List<GroupParametersDTO> toGroupParametersDTOList(List<GroupParameters> companies);
    GroupParameters fromGroupParametersDTO(GroupParametersDTO GroupParametersDTO);

    //GroupParameters
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void fillGroupParameters(GroupParameters source, @MappingTarget GroupParameters target);
    
}
